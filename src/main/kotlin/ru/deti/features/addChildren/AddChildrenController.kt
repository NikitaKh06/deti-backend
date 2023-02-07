package ru.deti.features.addChildren

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.deti.database.childrens.Childrens
import ru.deti.database.parents.Parents
import ru.deti.database.tokens.Tokens

class AddChildrenController {

    suspend fun addChildrenToParent(call: ApplicationCall) {
        val receiveAddModel = call.receive<ReceiveAddModel>()
        val parentId = Tokens.fetchUser(receiveAddModel.token)

        if(parentId == null) {
            call.respond(HttpStatusCode.Conflict, "Parent doesnt exist")
        }
        else {
            val parentModel = Parents.fetchParentWithId(parentId.user_id)
            if(parentModel != null) {
                val childrenModel = Childrens.fetchChildren(parentModel.email)
                if(childrenModel != null) {
                    Parents.addChildren(childrenId = childrenModel.id, parentId = parentModel.id)
                    call.respond(HttpStatusCode.OK)
                }
            }
        }
    }
}