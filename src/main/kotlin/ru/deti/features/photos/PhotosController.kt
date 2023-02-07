package ru.deti.features.photos

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.deti.database.childrens.Childrens
import ru.deti.database.parents.Parents
import ru.deti.database.photos.Photos
import ru.deti.database.photos.photosDTO
import ru.deti.database.tokens.Tokens

class PhotosController {
    suspend fun insertChildrenPhoto(call: ApplicationCall) {
        val photosReceiveModel = call.receive<ChildrenPhotosReceiveModel>()
        val childrenModel = Childrens.fetchChildren(photosReceiveModel.parent_email)
        if (childrenModel != null) {
        Photos.insert(
            photosDTO(
                user_id = childrenModel.id,
                photo = photosReceiveModel.photo
            )
        )
    }
    else{
        call.respond(HttpStatusCode.Conflict, "Children doest exist")
    }
    call.respond(HttpStatusCode.OK)
}

    suspend fun insertParentPhoto(call: ApplicationCall) {
        val photosReceiveModel = call.receive<ParentPhotoReceiveModel>()
        val parentModel = Parents.fetchParent(photosReceiveModel.email)
        if (parentModel != null) {
            Photos.insert(
                photosDTO(
                    user_id = parentModel.id,
                    photo = photosReceiveModel.photo
                )
            )
        }
        else{
            call.respond(HttpStatusCode.Conflict, "Parent does't exist")
        }
        call.respond(HttpStatusCode.OK)
    }

    suspend fun fetchPhoto(call: ApplicationCall) {
        val photosReceiveModel = call.receive<FetchPhotosReceiveModel>()

        val userModel = Tokens.fetchUser(photosReceiveModel.token)
        if(userModel != null) {
            val photoModel = Photos.fetchPhoto(userModel.user_id)

            if (photoModel != null) {
                call.respond(PhotoResponseModel(photo = photoModel.photo))
            }
            else {
                call.respond(HttpStatusCode.Conflict, "Photo doesn't exist")
            }
        }
        else {
            call.respond(HttpStatusCode.Conflict, "User with this token doesn't exist")
        }
    }
}