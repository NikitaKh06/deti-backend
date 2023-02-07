package ru.deti.features.children.login_children

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.deti.database.childrens.Childrens
import ru.deti.database.tokens.Tokens
import ru.deti.database.tokens.tokenDTO
import java.util.UUID

class LoginChildrenController {

    suspend fun performChildrenLogin(call: ApplicationCall) {
        val loginChildrenReceiveModel = call.receive<LoginChildrenReceiveModel>()
        val childrenDTO = Childrens.fetchChildren(loginChildrenReceiveModel.parent_email)

        if(childrenDTO == null) {
            call.respond(HttpStatusCode.Conflict, "Children does not exist")
        }
        else {
            val token = UUID.randomUUID().toString()
            if(loginChildrenReceiveModel.password == childrenDTO.password) {
                Tokens.deleteToken(childrenDTO.id)
                Tokens.insert(
                    tokenDTO(
                        user_id = childrenDTO.id,
                        token = token
                    )
                )
                call.respond(LoginChildrenResponceModel(token = token))
            }
            else {
                call.respond(HttpStatusCode.Conflict, "Invalid password")
            }
        }

    }
}