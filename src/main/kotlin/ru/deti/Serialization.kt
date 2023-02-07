package ru.deti

import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

fun Application.installSerialization() {
    install(ContentNegotiation) {
        json()
    }
}