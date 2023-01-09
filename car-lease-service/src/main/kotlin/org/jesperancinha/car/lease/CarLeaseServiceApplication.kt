package org.jesperancinha.car.lease

import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class CarLeaseServiceApplication {
    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(CarLeaseServiceApplication::class.java, *args)
        }
    }
}