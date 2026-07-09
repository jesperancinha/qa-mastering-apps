package org.jesperancinha.supermarket.delivery.api

import org.jesperancinha.supermarket.delivery.domain.DeliveryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/deliveries")
class DeliveryController(
    private val deliveryService: DeliveryService
) {

    @PostMapping
    fun createDelivery(
        @Valid @RequestBody request: DeliveryCreateRequest
    ): ResponseEntity<DeliveryResponse> {
        val created = deliveryService.createDelivery(
            vehicleId = request.vehicleId,
            address = request.address,
            startedAt = request.startedAt,
            finishedAt = request.finishedAt,
            status = request.status
        )
        return ResponseEntity.status(HttpStatus.CREATED).body(DeliveryResponse.fromDomain(created))
    }
}
