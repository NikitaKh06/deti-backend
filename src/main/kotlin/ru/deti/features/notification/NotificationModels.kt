package ru.deti.features.notification

import kotlinx.serialization.Serializable

@Serializable
data class NotificationReceiveModel(
    val subject_id: String,
    val hours: String,
    val minutes: String,
    val text: String
)

@Serializable
data class NotificationRespondModel(
    val hours: String,
    val minutes: String,
    val text: String
)

@Serializable
data class NotificationReadModel(
    val subject_id: String
)