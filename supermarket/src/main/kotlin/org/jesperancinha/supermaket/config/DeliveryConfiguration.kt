package org.jesperancinha.supermaket.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class DeliveryConfiguration {
    @Bean
    fun restTemplate() = RestTemplate()
}