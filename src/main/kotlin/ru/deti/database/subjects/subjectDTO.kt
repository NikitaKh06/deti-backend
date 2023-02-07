package ru.deti.database.subjects

import kotlinx.serialization.Serializable

data class subjectDTO(
    val id: String,
    val title: String,
    val day: String,
    val hours: String,
    val minutes: String,
    val children_id: String,
    val homework: String,
    val comment: String
)

@Serializable
data class subjectRespondArrayDTO(
    val id: String,
    val title: String,
    val day: String,
    val hours: String,
    val minutes: String
)