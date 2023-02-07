package ru.deti.features.notification

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureNotificationsRouting() {

    routing {
        post ("/notifications/insert") {
            val notificationController = NotificationController()
            notificationController.insertNotification(call)
        }

        post ("/notifications/fetch") {
            val notificationController = NotificationController()
            notificationController.fetchNotification(call)
        }
    }

}