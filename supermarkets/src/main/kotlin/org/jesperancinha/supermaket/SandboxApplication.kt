package org.jesperancinha.supermaket

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableTransactionManagement
@SpringBootApplication
class SandboxApplication

fun main(args: Array<String>) {
    runApplication<SandboxApplication>(*args)
}
