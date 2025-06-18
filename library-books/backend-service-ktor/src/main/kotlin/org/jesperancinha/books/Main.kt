package org.jesperancinha.books.org.jesperancinha.books

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
fun Application.module() {
    routing {
        get("/hello") {
            call.respondText("Hello, it's me!")
        }
    }
}

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}
