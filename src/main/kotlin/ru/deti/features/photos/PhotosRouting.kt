package ru.deti.features.photos

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configurePhotosRouting() {

    routing {
        post ("/photo/children/insert") {
            val photosController = PhotosController()
            photosController.insertChildrenPhoto(call)
        }

        post ("/photo/parent/insert") {
            val photosController = PhotosController()
            photosController.insertParentPhoto(call)
        }

        post ("/photo/fetch_photo") {
            val photosController = PhotosController()
            photosController.fetchPhoto(call)
        }
    }
}