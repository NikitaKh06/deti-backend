package ru.deti.features.readingProfiles

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureReadingRouting() {
    routing {
        post ("/read/children_from_children") {
            val readingProfileController = ReadingProfileController()
            readingProfileController.readChildrenProfileFromChildren(call)
        }

        post ("/read/parent") {
            val readingProfileController = ReadingProfileController()
            readingProfileController.readParent(call)
        }

        post ("/read/children_from_parent") {
            val readingProfileController = ReadingProfileController()
            readingProfileController.readChildrenFromParent(call)
        }

        post ("/read/subjects_from_children") {
            val readingProfileController = ReadingProfileController()
            readingProfileController.readSubjectsFromChildren(call)
        }

        post ("/read/subjects_from_parent") {
            val readingProfileController = ReadingProfileController()
            readingProfileController.readSubjectsFromParent(call)
        }

        post ("/read/full_subject") {
            val readingProfileController = ReadingProfileController()
            readingProfileController.readSubject(call)
        }
    }
}