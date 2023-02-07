package ru.deti.features.subject

import kotlinx.serialization.Serializable

//Insert new subject
@Serializable
data class SubjectReceiveModel(
    val token: String,
    val title: String,
    val day: String,
    val hours: String,
    val minutes: String
)

@Serializable
data class TextReceiveModel(
    val subject_id: String,
    val text: String
)