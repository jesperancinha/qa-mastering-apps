package org.jesperancinha.supermaket.config

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DeliveryConfigurationTest {

    private val configuration = DeliveryConfiguration()

    @Test
    fun `should create RestTemplate bean`() {
        // When
        val restTemplate = configuration.restTemplate()

        // Then
        assertNotNull(restTemplate)
    }
}