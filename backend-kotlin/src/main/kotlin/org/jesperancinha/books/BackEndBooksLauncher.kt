package org.jesperancinha.books

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class BackEndBooksLauncher

fun main(args: Array<String>) {
    SpringApplication.run(BackEndBooksLauncher::class.java, *args).start()
}