package ru.deti.database.tokens

data class tokenDTO(
    val user_id: String,
    val token: String
)

data class FetchUserResponceDTO(
    val user_id: String
)