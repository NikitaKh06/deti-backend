package ru.deti.database.childrens

data class childrenRegisterDTO (
    val id: String,
    val parent_email: String,
    val password: String,
    val first_name: String,
    val last_name: String,
    val age: String
)