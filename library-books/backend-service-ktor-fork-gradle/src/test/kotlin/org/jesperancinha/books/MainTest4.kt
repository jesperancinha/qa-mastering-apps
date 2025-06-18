package org.jesperancinha.books

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.testing.*
import kotlinx.coroutines.delay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.lang.management.ManagementFactory
import kotlin.time.Duration.Companion.seconds

class ApplicationTest4 {

    private fun testApp(): Application.() -> Unit = {
        install(ContentNegotiation) { gson() }
        routing {
            get("/hello") { call.respondText("Hello, it's me!") }
        }
    }

    @Test
    fun testHelloEndpoint1() = testApplication {
        commonTest()
    }


    @Test
    fun testHelloEndpoint2() = testApplication {
        commonTest()
    }

    @Test
    fun testHelloEndpoint3() = testApplication {
        commonTest()
    }

    @Test
    fun testHelloEndpoint4() = testApplication {
        commonTest()
    }

    @Test
    fun testHelloEndpoint5() = testApplication {
        commonTest()
    }

    @Test
    fun testHelloEndpoint6() = testApplication {
        commonTest()
    }

    private suspend fun ApplicationTestBuilder.commonTest() {
        println(Thread.currentThread())
        println("Test running with identityHashCode: ${System.identityHashCode(this)}")
        val pid = ManagementFactory.getRuntimeMXBean().name.split("@")[0]
        println("Running in JVM with PID: $pid")
        application(testApp())
        client.get("/hello").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello, it's me!", bodyAsText())
        }
        delay(5.seconds)
    }

    companion object {
        @BeforeAll
        @JvmStatic
        fun setup() {
            println("All tests started")
        }
    }
}
