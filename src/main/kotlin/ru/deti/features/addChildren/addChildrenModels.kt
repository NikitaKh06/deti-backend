package ru.deti.features.addChildren

import kotlinx.serialization.Serializable

@Serializable
data class ReceiveAddModel(
    val token: String
)