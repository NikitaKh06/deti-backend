package ru.deti.features.children.register_children

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRegisterChildrenRouting() {

    routing {
        post ("/children/register") {
            val registerChildrenController = RegisterChildrenController()
            registerChildrenController.registerNewChildren(call)
            call.respond(HttpStatusCode.OK)
        }
    }
}