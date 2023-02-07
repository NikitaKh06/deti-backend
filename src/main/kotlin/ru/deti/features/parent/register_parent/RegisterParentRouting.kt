package ru.deti.features.parent.register_parent

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRegisterParentRouting() {

    routing {
        post ("/parent/register") {
            val registerParentController = RegisterParentController()
            registerParentController.registerNewParent(call)
            call.respond(HttpStatusCode.OK)
        }
    }
}