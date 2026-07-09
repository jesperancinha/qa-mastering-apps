package org.jesperancinha.books

import io.kotest.matchers.shouldBe
import io.mockk.mockk
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.testing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.gson.*
import org.jesperancinha.books.org.jesperancinha.books.module
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode

class ApplicationTest {

    @Test
    fun testHelloEndpoint() {
        testApplication {
            application {
                module()
            }
            client.get("/hello").apply {
                status shouldBe HttpStatusCode.OK
                bodyAsText() shouldBe "Hello, it's me!"
            }
        }
    }
}
