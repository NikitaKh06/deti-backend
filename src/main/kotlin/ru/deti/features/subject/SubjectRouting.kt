package ru.deti.features.subject

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureSubjectRouting() {
    routing {
        post ("/subject/insert") {
            val subjectController = SubjectController()
            subjectController.insertSubject(call)
        }

        post ("/subject/add_comment") {
            val subjectController = SubjectController()
            subjectController.addComment(call)
        }

        post ("/subject/add_homework") {
            val subjectController = SubjectController()
            subjectController.addHomework(call)
        }
    }
}