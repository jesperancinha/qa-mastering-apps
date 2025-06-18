package org.jesperancinha.shop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableTransactionManagement
@SpringBootApplication
class OnlineShopAppApplication

fun main(args: Array<String>) {
	runApplication<OnlineShopAppApplication>(*args)
}
