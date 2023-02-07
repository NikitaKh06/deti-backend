package ru.deti.features.readingProfiles

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.deti.database.childrens.Childrens
import ru.deti.database.parents.Parents
import ru.deti.database.photos.Photos
import ru.deti.database.qrs.Qrs
import ru.deti.database.subjects.Subjects
import ru.deti.database.tokens.Tokens

class ReadingProfileController {

    suspend fun readChildrenProfileFromChildren(call: ApplicationCall) {
        val rModelForReadChildrenFChildren = call.receive<ReceiveReadModel>()
        val childrenId = Tokens.fetchUser(rModelForReadChildrenFChildren.token)

        if(childrenId == null) {
            call.respond(HttpStatusCode.Conflict, "Children doesnt exist")
        }
        else {
            val childrenModel = Childrens.fetchChildrenWithId(childrenId.user_id)
            if(childrenModel != null) {
                val photoModel = Photos.fetchPhoto(childrenId.user_id)
                val qrModel = Qrs.fetchQR(childrenId.user_id)
                if(photoModel != null && qrModel != null) {
                    call.respond(
                        ResponceModelForChildrenProfile(
                            first_name = childrenModel.first_name,
                            last_name = childrenModel.last_name,
                            age = childrenModel.age,
                            photo = photoModel.photo,
                            qr_code =qrModel.qr_code
                        )
                    )
                }
            }
            else {
                call.respond(HttpStatusCode.Conflict, "Children doesnt exist")
            }
        }
    }

    suspend fun readParent(call: ApplicationCall) {
        val receiveReadModel = call.receive<ReceiveReadModel>()
        val parentId = Tokens.fetchUser(receiveReadModel.token)

        if(parentId == null) {
            call.respond(HttpStatusCode.Conflict, "Parent doesnt exist")
        }
        else {
            val parentModel = Parents.fetchParentWithId(parentId.user_id)
            if(parentModel != null) {
                val photoModel = Photos.fetchPhoto(parentId.user_id)
                call.respond(
                    ResponceModelReadParent(
                        first_name = parentModel.first_name,
                        last_name = parentModel.last_name,
                        photo = photoModel?.photo ?: "null"
                    )
                )
            }
            else {
                call.respond(HttpStatusCode.Conflict, "Parent doesnt exist")
            }
        }
    }

    suspend fun readChildrenFromParent(call: ApplicationCall) {
        val receiveReadModel = call.receive<ReceiveReadModel>()
        val parentId = Tokens.fetchUser(receiveReadModel.token)

        if (parentId == null) {
            call.respond(HttpStatusCode.Conflict, "Parent doesnt exist")
        }
        else {
            val parentModel = Parents.fetchParentWithId(parentId.user_id)
            if(parentModel != null) {
                val childrenId = parentModel.children_id
                if(childrenId != "null") {
                    val childrenModel = Childrens.fetchChildrenWithId(childrenId)
                    val photoModel = Photos.fetchPhoto(childrenId)
                    if(childrenModel != null && photoModel != null) {
                        call.respond(
                            ResponceModelReadChildrenFromParent(
                                first_name = childrenModel.first_name,
                                last_name = childrenModel.last_name,
                                age = childrenModel.age,
                                photo = photoModel.photo
                            )
                        )
                    }
                }
                else {
                    call.respond(HttpStatusCode.Conflict, "Parent dont have children")
                }
            }
        }
    }

    suspend fun readSubjectsFromChildren(call: ApplicationCall) {
        val receiveReadModel = call.receive<ReceiveReadArraySubjectModel>()
        val childrenId = Tokens.fetchUser(receiveReadModel.token)
        if(childrenId != null) {
            val childrenModel = Childrens.fetchChildrenWithId(childrenId.user_id)
            if(childrenModel != null) {
                val children_id = childrenId.user_id
                val day = receiveReadModel.day
                val subjects = Subjects.fetchSubjectToArray(children_id = children_id, day = day)
                if(subjects != null) {
                    call.respond(subjects)
                }
                else {
                    call.respond(HttpStatusCode.Conflict, "There are no subjects")
                }
            }
            else {
                call.respond(HttpStatusCode.Conflict, "Children doesnt exist")
            }
        }
        else {
            call.respond(HttpStatusCode.Conflict, "Invalid token")
        }
    }

    suspend fun readSubjectsFromParent(call: ApplicationCall) {
        val receiveReadModel = call.receive<ReceiveReadArraySubjectModel>()
        val parentId = Tokens.fetchUser(receiveReadModel.token)
        if(parentId != null) {
            val parentModel = Parents.fetchParentWithId(parentId.user_id)
            if(parentModel != null) {
                val children_id = parentModel.children_id
                val day = receiveReadModel.day
                val subjects = Subjects.fetchSubjectToArray(children_id = children_id, day = day)
                if(subjects != null) {
                    call.respond(subjects)
                }
                else {
                    call.respond(HttpStatusCode.Conflict, "There are no subjects")
                }
            }
            else {
                call.respond(HttpStatusCode.Conflict, "Parent doesnt exist")
            }
        }
        else {
            call.respond(HttpStatusCode.Conflict, "Invalid token")
        }
    }

    suspend fun readSubject(call: ApplicationCall) {
        val readFullSubjectReceiveModel = call.receive<ReadFullSubjectReceiveModel>()
        val subjectModel = Subjects.fetchSubject(readFullSubjectReceiveModel.subject_id)
        if(subjectModel != null) {
            call.respond(
                ResponceReadingSubject(
                    id = subjectModel.id,
                    title = subjectModel.title,
                    day = subjectModel.day,
                    hours = subjectModel.hours,
                    minutes = subjectModel.minutes,
                    comment = subjectModel.comment,
                    homework = subjectModel.homework
                )
            )
        }
        else {
            call.respond(HttpStatusCode.Conflict, "Subject doesn't exist")
        }
    }
}