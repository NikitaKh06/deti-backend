package ru.deti.features.qrs

import kotlinx.serialization.Serializable

@Serializable
data class QRsInsertReceiveModel(
    val parent_email: String,
    val qr_code: String
)

@Serializable
data class QRsFetchReceiveModel(
    val token: String
)

@Serializable
data class QRsResponceModel(
    val qr_code: String
)
