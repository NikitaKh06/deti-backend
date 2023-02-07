package ru.deti.features.children.register_children

import kotlinx.serialization.Serializable

@Serializable
data class ChildrenRegistrationReceiveModel (
    val parent_email: String,
    val password: String,
    val first_name: String,
    val last_name: String,
    val age: String
)

@Serializable
data class ChildrenRegistrationResponceModel(
    val token: String
)