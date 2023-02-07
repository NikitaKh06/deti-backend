package ru.deti.features.parent.login_parent

import kotlinx.serialization.Serializable

@Serializable
data class LoginParentReceiveModel (
    val email: String,
    val password: String
)

@Serializable
data class LoginParentResponceModel (
    val token: String
)