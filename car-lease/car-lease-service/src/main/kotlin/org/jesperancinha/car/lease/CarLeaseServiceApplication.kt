package org.jesperancinha.car.lease

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class CarLeaseServiceApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(CarLeaseServiceApplication::class.java, *args)
        }
    }
}