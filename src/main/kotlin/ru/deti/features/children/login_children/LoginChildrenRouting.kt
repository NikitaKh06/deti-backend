package ru.deti.features.children.login_children

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureLoginChildrenRouting() {

    routing {
        post ("/children/login") {
            val loginChildrenController = LoginChildrenController()
            loginChildrenController.performChildrenLogin(call)
        }
    }
}