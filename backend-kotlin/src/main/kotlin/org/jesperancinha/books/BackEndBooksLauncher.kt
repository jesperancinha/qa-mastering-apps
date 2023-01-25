package org.jesperancinha.books

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class BackEndBooksLauncher

fun main(args: Array<String>) {
    SpringApplication.run(BackEndBooksLauncher::class.java, *args)
}