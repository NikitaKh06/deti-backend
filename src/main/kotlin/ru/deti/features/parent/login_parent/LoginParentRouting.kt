package ru.deti.features.parent.login_parent

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureLoginParentRouting() {

    routing {
        post ("/parent/login") {
            val loginParentController = LoginParentController()
            loginParentController.performParentLogin(call)
        }
    }
}