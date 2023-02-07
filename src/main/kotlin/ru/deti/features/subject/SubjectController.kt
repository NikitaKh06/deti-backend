package ru.deti.features.subject

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.deti.database.parents.Parents
import ru.deti.database.subjects.Subjects
import ru.deti.database.subjects.subjectDTO
import ru.deti.database.tokens.Tokens
import kotlin.random.Random

class SubjectController {

    suspend fun insertSubject(call: ApplicationCall) {
        val subjectReceiveModel = call.receive<SubjectReceiveModel>()
        val parentId = Tokens.fetchUser(subjectReceiveModel.token)
        if(parentId != null) {
            val parentModel = Parents.fetchParentWithId(parentId.user_id)
            if(parentModel != null) {
                val childrenId = parentModel.children_id
                val subjectId = Random.nextInt().toString()
                Subjects.insert(
                    subjectDTO(
                        id = subjectId,
                        title = subjectReceiveModel.title,
                        day = subjectReceiveModel.day,
                        hours = subjectReceiveModel.hours,
                        minutes = subjectReceiveModel.minutes,
                        children_id = childrenId,
                        homework = "",
                        comment = ""
                    )
                )
                call.respond(HttpStatusCode.OK)
            }
            else {
                call.respond(HttpStatusCode.Conflict, "Parent doesn't exist")
            }
        }
        else {
            call.respond(HttpStatusCode.Conflict, "Parent with this token doesn't exist")
        }
    }

    suspend fun addComment(call: ApplicationCall) {
        val textReceiveModel = call.receive<TextReceiveModel>()
        val subject_id = textReceiveModel.subject_id
        val subjectModel = Subjects.fetchSubject(subject_id)

        if(subjectModel != null) {
            val text = textReceiveModel.text
            Subjects.addComment(subject_id, text)
            call.respond(HttpStatusCode.OK)
        }
        else {
            call.respond(HttpStatusCode.Conflict, "Subject doesnt exist")
        }
    }

    suspend fun addHomework(call: ApplicationCall) {
        val textReceiveModel = call.receive<TextReceiveModel>()
        val subject_id = textReceiveModel.subject_id
        val subjectModel = Subjects.fetchSubject(subject_id)

        if(subjectModel != null) {
            val text = textReceiveModel.text
            Subjects.addHomework(subject_id, text)
            call.respond(HttpStatusCode.OK)
        }
        else {
            call.respond(HttpStatusCode.Conflict, "Subject doesnt exist")
        }
    }
}