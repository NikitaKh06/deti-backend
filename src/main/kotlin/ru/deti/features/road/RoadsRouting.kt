package ru.deti.features.road

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRoadsRouting() {

    routing {
        post ("/road/insert") {
            val roadController = RoadController()
            roadController.insertRoad(call)
        }
    }
}