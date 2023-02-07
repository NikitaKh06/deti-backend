package ru.deti.features.parent.login_parent

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.deti.database.parents.Parents
import ru.deti.database.tokens.Tokens
import ru.deti.database.tokens.tokenDTO
import java.util.UUID

class LoginParentController {

    suspend fun performParentLogin(call: ApplicationCall) {
        val loginParentReceiveModel = call.receive<LoginParentReceiveModel>()
        val parentDTO = Parents.fetchParent(loginParentReceiveModel.email)

        if(parentDTO == null) {
            call.respond(HttpStatusCode.Conflict, "Parent does not exist")
        }
        else {
            val token = UUID.randomUUID().toString()
            if(loginParentReceiveModel.password == parentDTO.password) {
                Tokens.deleteToken(parentDTO.id)
                Tokens.insert(
                    tokenDTO(
                        user_id = parentDTO.id,
                        token = token
                    )
                )
                call.respond(LoginParentResponceModel(token = token))
            }
            else {
                call.respond(HttpStatusCode.Conflict, "Invalid password")
            }
        }

    }
}