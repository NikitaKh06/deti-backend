package ru.deti.features.photos

import kotlinx.serialization.Serializable

@Serializable
data class ChildrenPhotosReceiveModel(
    val parent_email: String,
    val photo: String
)

@Serializable
data class ParentPhotoReceiveModel(
    val email: String,
    val photo: String
)

@Serializable
data class FetchPhotosReceiveModel(
    val token: String
)

@Serializable
data class PhotoResponseModel (
    val photo: String
)