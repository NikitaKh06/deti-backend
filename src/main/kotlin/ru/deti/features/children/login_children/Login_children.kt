package ru.deti.features.children.login_children

import kotlinx.serialization.Serializable

@Serializable
data class LoginChildrenReceiveModel (
    val parent_email: String,
    val password: String
)

@Serializable
data class LoginChildrenResponceModel (
    val token: String
)