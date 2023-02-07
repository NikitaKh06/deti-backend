package ru.deti.features.parent.register_parent

import kotlinx.serialization.Serializable

@Serializable
data class ParentRegistrationReceiveModel (
    val email: String,
    val password: String,
    val first_name: String,
    val last_name: String
)

@Serializable
data class ParentRegistrationResponceModel(
    val token: String
)