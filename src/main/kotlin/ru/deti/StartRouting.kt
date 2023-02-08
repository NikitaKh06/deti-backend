package ru.deti

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureStartingRouting() {

    routing {
        get("/") {
            call.respond(HttpStatusCode.OK, "Hello world!")
        }
    }
}