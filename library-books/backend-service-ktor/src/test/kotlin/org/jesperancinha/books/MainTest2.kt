package org.jesperancinha.books

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.testing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.gson.*
import kotlinx.coroutines.delay
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import kotlin.time.Duration.Companion.seconds

class ApplicationTest2 {

    private fun testApp(): Application.() -> Unit = {
        install(ContentNegotiation) { gson() }
        routing {
            get("/hello") { call.respondText("Hello, it's me!") }
        }
    }

    @Test
    fun testHelloEndpoint1() = commonTest()

    @Test
    fun testHelloEndpoint2() = commonTest()

    private fun commonTest() {
        testApplication {
            application(testApp())
            client.get("/hello").apply {
                assertEquals(HttpStatusCode.OK, status)
                assertEquals("Hello, it's me!", bodyAsText())
                delay(5.seconds)
            }
        }
    }
}
