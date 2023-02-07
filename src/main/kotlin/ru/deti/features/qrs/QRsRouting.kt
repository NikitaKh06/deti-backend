package ru.deti.features.qrs

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureQRsRouting() {

    routing {
        post ("/qr/insert") {
            val qRsController = QRsController()
            qRsController.insertQR(call)
        }

        post ("/qr/fetch") {
            val qRsController = QRsController()
            qRsController.fetchQR(call)
        }
    }
}