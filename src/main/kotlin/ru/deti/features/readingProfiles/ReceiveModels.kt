package ru.deti.features.readingProfiles

import kotlinx.serialization.Serializable

@Serializable
data class ReceiveReadModel(
    val token: String
)

//Data model for reading children profile from children
@Serializable
data class ResponceModelForChildrenProfile(
    val first_name: String,
    val last_name: String,
    val age: String,
    val photo: String,
    val qr_code: String
)

//Data model for reading parent profile from parent
@Serializable
data class ResponceModelReadParent(
    val first_name: String,
    val last_name: String,
    val photo: String
)

//Data model for reading children from parent
@Serializable
data class ResponceModelReadChildrenFromParent(
    val first_name: String,
    val last_name: String,
    val age: String,
    val photo: String
)

//Data model for reading subject to array
@Serializable
data class ReceiveReadArraySubjectModel(
    val token: String,
    val day: String
)

//Data model for reading subject
@Serializable
data class ResponceReadingSubject(
    val id: String,
    val title: String,
    val day: String,
    val hours: String,
    val minutes: String,
    val comment: String,
    val homework: String,
)

//Data model for reading full subject
@Serializable
data class ReadFullSubjectReceiveModel(
    val subject_id: String
)