package ru.deti.features.notification

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.deti.database.notifications.Notifications
import ru.deti.database.notifications.notificationsDTO

class NotificationController {

    suspend fun insertNotification(call: ApplicationCall) {
        val notificationReceiveModel = call.receive<NotificationReceiveModel>()
        Notifications.insert(
            notificationsDTO(
                subject_id = notificationReceiveModel.subject_id,
                hours = notificationReceiveModel.hours,
                minutes = notificationReceiveModel.minutes,
                text = notificationReceiveModel.text
            )
        )
        call.respond(HttpStatusCode.OK)
    }

    suspend fun fetchNotification(call: ApplicationCall) {
        val notificationReadModel = call.receive<NotificationReadModel>()
        val notificationModel = Notifications.fetchNotification(notificationReadModel.subject_id)
        if(notificationModel != null) {
            call.respond(
                NotificationRespondModel(
                    hours = notificationModel.hours,
                    minutes = notificationModel.minutes,
                    text = notificationModel.text
                )
            )
        }
        else {
            call.respond(HttpStatusCode.Conflict, "Notification doesn't exist")
        }
    }
}