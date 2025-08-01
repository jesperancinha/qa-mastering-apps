package org.jesperancinha.supermaket.service

import io.mockk.*
import org.jesperancinha.supermaket.domain.Delivery
import org.jesperancinha.supermaket.domain.DeliveryStatus
import org.jesperancinha.supermaket.dto.*
import org.jesperancinha.supermaket.repository.DeliveryRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.client.RestTemplate
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.*

class DeliveryServiceTest {

    private lateinit var deliveryRepository: DeliveryRepository
    private lateinit var restTemplate: RestTemplate
    private lateinit var deliveryService: DeliveryService
    private val externalApiUrl = "http://invoice-service.example.com"

    @BeforeEach
    fun setUp() {
        deliveryRepository = mockk()
        restTemplate = mockk()
        deliveryService = DeliveryService(deliveryRepository, restTemplate, externalApiUrl)
    }

    @Test
    fun `should create delivery with IN_PROGRESS status`() {
        // Given
        val now = Instant.now()
        val requestDto = DeliveryRequestDto(
            vehicleId = "vehicle1",
            address = "123 Main St",
            startedAt = now,
            finishedAt = null,
            status = DeliveryStatus.IN_PROGRESS
        )

        val savedDelivery = Delivery(
            id = UUID.randomUUID(),
            vehicleId = "vehicle1",
            address = "123 Main St",
            startedAt = now,
            finishedAt = null,
            status = DeliveryStatus.IN_PROGRESS
        )

        // When
        every { deliveryRepository.save(any()) } returns savedDelivery

        val result = deliveryService.createDelivery(requestDto)

        // Then
        verify { deliveryRepository.save(any()) }
        assertEquals(savedDelivery.id, result.id)
        assertEquals(requestDto.vehicleId, result.vehicleId)
        assertEquals(requestDto.address, result.address)
        assertEquals(requestDto.startedAt, result.startedAt)
        assertEquals(requestDto.finishedAt, result.finishedAt)
        assertEquals(requestDto.status, result.status)
    }

    @Test
    fun `should create delivery with DELIVERED status`() {
        // Given
        val now = Instant.now()
        val finishedAt = now.plus(2, ChronoUnit.HOURS)
        val requestDto = DeliveryRequestDto(
            vehicleId = "vehicle1",
            address = "123 Main St",
            startedAt = now,
            finishedAt = finishedAt,
            status = DeliveryStatus.DELIVERED
        )

        val savedDelivery = Delivery(
            id = UUID.randomUUID(),
            vehicleId = "vehicle1",
            address = "123 Main St",
            startedAt = now,
            finishedAt = finishedAt,
            status = DeliveryStatus.DELIVERED
        )

        // When
        every { deliveryRepository.save(any()) } returns savedDelivery

        val result = deliveryService.createDelivery(requestDto)

        // Then
        verify { deliveryRepository.save(any()) }
        assertEquals(savedDelivery.id, result.id)
        assertEquals(requestDto.vehicleId, result.vehicleId)
        assertEquals(requestDto.address, result.address)
        assertEquals(requestDto.startedAt, result.startedAt)
        assertEquals(requestDto.finishedAt, result.finishedAt)
        assertEquals(requestDto.status, result.status)
    }

    @Test
    fun `should throw exception when IN_PROGRESS delivery has finishedAt`() {
        // Given
        val now = Instant.now()
        val requestDto = DeliveryRequestDto(
            vehicleId = "vehicle1",
            address = "123 Main St",
            startedAt = now,
            finishedAt = now.plus(1, ChronoUnit.HOURS),
            status = DeliveryStatus.IN_PROGRESS
        )

        // When/Then
        assertThrows<IllegalArgumentException> {
            deliveryService.createDelivery(requestDto)
        }
        
        verify(exactly = 0) { deliveryRepository.save(any()) }
    }

    @Test
    fun `should throw exception when DELIVERED delivery has null finishedAt`() {
        // Given
        val now = Instant.now()
        val requestDto = DeliveryRequestDto(
            vehicleId = "vehicle1",
            address = "123 Main St",
            startedAt = now,
            finishedAt = null,
            status = DeliveryStatus.DELIVERED
        )

        // When/Then
        assertThrows<IllegalArgumentException> {
            deliveryService.createDelivery(requestDto)
        }
        
        verify(exactly = 0) { deliveryRepository.save(any()) }
    }

    @Test
    fun `should get invoices for deliveries`() {
        // Given
        val deliveryId1 = UUID.randomUUID()
        val deliveryId2 = UUID.randomUUID()
        val invoiceId1 = UUID.randomUUID()
        val invoiceId2 = UUID.randomUUID()
        
        val delivery1 = Delivery(
            id = deliveryId1,
            vehicleId = "vehicle1",
            address = "123 Main St",
            startedAt = Instant.now(),
            status = DeliveryStatus.DELIVERED,
            finishedAt = Instant.now().plus(1, ChronoUnit.HOURS)
        )
        
        val delivery2 = Delivery(
            id = deliveryId2,
            vehicleId = "vehicle2",
            address = "456 Oak Ave",
            startedAt = Instant.now(),
            status = DeliveryStatus.DELIVERED,
            finishedAt = Instant.now().plus(2, ChronoUnit.HOURS)
        )
        
        val request = InvoiceRequestDto(listOf(deliveryId1, deliveryId2))
        
        // When
        every { deliveryRepository.findByIdOrNull(deliveryId1) } returns delivery1
        every { deliveryRepository.findByIdOrNull(deliveryId2) } returns delivery2
        
        every { 
            restTemplate.postForObject(
                "$externalApiUrl/v1/invoices",
                any<SendInvoiceRequestDto>(),
                SendInvoiceResponseDto::class.java
            )
        } answers { 
            val req = secondArg<SendInvoiceRequestDto>()
            when (req.deliveryId) {
                deliveryId1 -> SendInvoiceResponseDto(invoiceId1, true)
                deliveryId2 -> SendInvoiceResponseDto(invoiceId2, true)
                else -> null
            }
        }
        
        val result = deliveryService.getInvoicesForDeliveries(request)
        
        // Then
        assertEquals(2, result.size)
        assertEquals(deliveryId1, result[0].deliveryId)
        assertEquals(invoiceId1, result[0].invoiceId)
        assertEquals(deliveryId2, result[1].deliveryId)
        assertEquals(invoiceId2, result[1].invoiceId)
        
        verify(exactly = 1) { deliveryRepository.findByIdOrNull(deliveryId1) }
        verify(exactly = 1) { deliveryRepository.findByIdOrNull(deliveryId2) }
        verify(exactly = 2) { 
            restTemplate.postForObject(
                "$externalApiUrl/v1/invoices",
                any<SendInvoiceRequestDto>(),
                SendInvoiceResponseDto::class.java
            )
        }
    }
    
    @Test
    fun `should throw exception when delivery not found`() {
        // Given
        val deliveryId = UUID.randomUUID()
        val request = InvoiceRequestDto(listOf(deliveryId))
        
        // When
        every { deliveryRepository.findByIdOrNull(deliveryId) } returns null
        
        // Then
        assertThrows<RuntimeException> {
            deliveryService.getInvoicesForDeliveries(request)
        }
        
        verify(exactly = 1) { deliveryRepository.findByIdOrNull(deliveryId) }
        verify(exactly = 0) { 
            restTemplate.postForObject(
                any<String>(),
                any<SendInvoiceRequestDto>(),
                SendInvoiceResponseDto::class.java
            )
        }
    }
    
    @Test
    fun `should throw exception when invoice service returns null`() {
        // Given
        val deliveryId = UUID.randomUUID()
        val delivery = Delivery(
            id = deliveryId,
            vehicleId = "vehicle1",
            address = "123 Main St",
            startedAt = Instant.now(),
            status = DeliveryStatus.DELIVERED,
            finishedAt = Instant.now().plus(1, ChronoUnit.HOURS)
        )
        
        val request = InvoiceRequestDto(listOf(deliveryId))
        
        // When
        every { deliveryRepository.findByIdOrNull(deliveryId) } returns delivery
        every { 
            restTemplate.postForObject(
                "$externalApiUrl/v1/invoices",
                any<SendInvoiceRequestDto>(),
                SendInvoiceResponseDto::class.java
            )
        } returns null
        
        // Then
        assertThrows<RuntimeException> {
            deliveryService.getInvoicesForDeliveries(request)
        }
        
        verify(exactly = 1) { deliveryRepository.findByIdOrNull(deliveryId) }
        verify(exactly = 1) { 
            restTemplate.postForObject(
                "$externalApiUrl/v1/invoices",
                any<SendInvoiceRequestDto>(),
                SendInvoiceResponseDto::class.java
            )
        }
    }
    
    @Test
    fun `should get yesterday deliveries summary`() {
        // Given
        val zoneId = ZoneId.of("Europe/Amsterdam")
        val today = LocalDate.now(zoneId)
        val yesterday = today.minusDays(1)
        val startOfYesterday = yesterday.atStartOfDay(zoneId).toInstant()
        val endOfYesterday = yesterday.plusDays(1).atStartOfDay(zoneId).toInstant()
        
        val delivery1 = Delivery(
            id = UUID.randomUUID(),
            vehicleId = "vehicle1",
            address = "123 Main St",
            startedAt = startOfYesterday.plus(2, ChronoUnit.HOURS),
            status = DeliveryStatus.DELIVERED,
            finishedAt = startOfYesterday.plus(3, ChronoUnit.HOURS)
        )
        
        val delivery2 = Delivery(
            id = UUID.randomUUID(),
            vehicleId = "vehicle2",
            address = "456 Oak Ave",
            startedAt = startOfYesterday.plus(4, ChronoUnit.HOURS),
            status = DeliveryStatus.DELIVERED,
            finishedAt = startOfYesterday.plus(5, ChronoUnit.HOURS)
        )
        
        val delivery3 = Delivery(
            id = UUID.randomUUID(),
            vehicleId = "vehicle3",
            address = "789 Pine St",
            startedAt = startOfYesterday.plus(6, ChronoUnit.HOURS),
            status = DeliveryStatus.DELIVERED,
            finishedAt = startOfYesterday.plus(7, ChronoUnit.HOURS)
        )
        
        val deliveries = listOf(delivery1, delivery2, delivery3)
        
        // When
        every { 
            deliveryRepository.findByStartedAtBetweenOrderByStartedAtAsc(startOfYesterday, endOfYesterday) 
        } returns deliveries
        
        val result = deliveryService.getYesterdayDeliveriesSummary()
        
        // Then
        assertEquals(3, result.deliveries)
        assertEquals(120L, result.averageMinutesBetweenDeliveryStart)
        
        verify(exactly = 1) { 
            deliveryRepository.findByStartedAtBetweenOrderByStartedAtAsc(startOfYesterday, endOfYesterday) 
        }
    }
    
    @Test
    fun `should return zero average when only one delivery`() {
        // Given
        val zoneId = ZoneId.of("Europe/Amsterdam")
        val today = LocalDate.now(zoneId)
        val yesterday = today.minusDays(1)
        val startOfYesterday = yesterday.atStartOfDay(zoneId).toInstant()
        val endOfYesterday = yesterday.plusDays(1).atStartOfDay(zoneId).toInstant()
        
        val delivery = Delivery(
            id = UUID.randomUUID(),
            vehicleId = "vehicle1",
            address = "123 Main St",
            startedAt = startOfYesterday.plus(2, ChronoUnit.HOURS),
            status = DeliveryStatus.DELIVERED,
            finishedAt = startOfYesterday.plus(3, ChronoUnit.HOURS)
        )
        
        // When
        every { 
            deliveryRepository.findByStartedAtBetweenOrderByStartedAtAsc(startOfYesterday, endOfYesterday) 
        } returns listOf(delivery)
        
        val result = deliveryService.getYesterdayDeliveriesSummary()
        
        // Then
        assertEquals(1, result.deliveries)
        assertEquals(0L, result.averageMinutesBetweenDeliveryStart)
        
        verify(exactly = 1) { 
            deliveryRepository.findByStartedAtBetweenOrderByStartedAtAsc(startOfYesterday, endOfYesterday) 
        }
    }
    
    @Test
    fun `should return zero average when no deliveries`() {
        // Given
        val zoneId = ZoneId.of("Europe/Amsterdam")
        val today = LocalDate.now(zoneId)
        val yesterday = today.minusDays(1)
        val startOfYesterday = yesterday.atStartOfDay(zoneId).toInstant()
        val endOfYesterday = yesterday.plusDays(1).atStartOfDay(zoneId).toInstant()
        
        // When
        every { 
            deliveryRepository.findByStartedAtBetweenOrderByStartedAtAsc(startOfYesterday, endOfYesterday) 
        } returns emptyList()
        
        val result = deliveryService.getYesterdayDeliveriesSummary()
        
        // Then
        assertEquals(0, result.deliveries)
        assertEquals(0L, result.averageMinutesBetweenDeliveryStart)
        
        verify(exactly = 1) { 
            deliveryRepository.findByStartedAtBetweenOrderByStartedAtAsc(startOfYesterday, endOfYesterday) 
        }
    }
}