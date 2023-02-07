package ru.deti.database.notifications

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Notifications : Table() {
    private val subject_id = Notifications.varchar("subject_id", 50)
    private val hours = Notifications.varchar("hours", 2)
    private val minutes = Notifications.varchar("minutes", 2)
    private val text = Notifications.varchar("text", 100)

    fun insert(notificationsDTO: notificationsDTO) {
        transaction {
            Notifications.insert {
                it[subject_id] = notificationsDTO.subject_id
                it[hours] = notificationsDTO.hours
                it[minutes] = notificationsDTO.minutes
                it[text] = notificationsDTO.text
            }
        }
    }

    fun fetchNotification(subject_id: String) : notificationsDTO? {
        return try {
            transaction {
                val notificationModel = Notifications.select { Notifications.subject_id eq subject_id }.single()
                notificationsDTO(
                    subject_id = notificationModel[Notifications.subject_id],
                    hours = notificationModel[Notifications.hours],
                    minutes = notificationModel[Notifications.minutes],
                    text = notificationModel[Notifications.text]
                )
            }
        } catch (e: Exception) { null }
    }
}