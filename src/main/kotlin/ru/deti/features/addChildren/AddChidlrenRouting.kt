package ru.deti.features.addChildren

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureAddChildrenRouting() {

    routing {
        post ("/add_children") {
            val addChildrenController = AddChildrenController()
            addChildrenController.addChildrenToParent(call)
        }
    }
}