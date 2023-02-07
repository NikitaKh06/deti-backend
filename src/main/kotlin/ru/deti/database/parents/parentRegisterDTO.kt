package ru.deti.database.parents

data class parentRegisterDTO (
    val id: String,
    val email: String,
    val password: String,
    val first_name: String,
    val last_name: String,
    val children_id: String
)