package ru.deti.features.children.register_children

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import ru.deti.database.childrens.Childrens
import ru.deti.database.childrens.childrenRegisterDTO
import ru.deti.database.tokens.Tokens
import ru.deti.database.tokens.tokenDTO
import ru.deti.utils.isValidEmail
import kotlin.random.Random
import java.util.UUID

class RegisterChildrenController () {

    suspend fun registerNewChildren(call: ApplicationCall) {
        val childrenRegistrationReceiveModel = call.receive<ChildrenRegistrationReceiveModel>()

        if(!childrenRegistrationReceiveModel.parent_email.isValidEmail()) {
            call.respond(HttpStatusCode.Conflict, "Parent email is not valid")
        }

        val childrenDTO =  Childrens.fetchChildren(childrenRegistrationReceiveModel.parent_email)

        if(childrenDTO != null) {
            call.respond(HttpStatusCode.Conflict, "Children already exist")
        }
        else {
            val token = UUID.randomUUID().toString()
            val id = Random.nextInt().toString()
            try {
                Childrens.insert(
                    childrenRegisterDTO(
                        id = id,
                        parent_email = childrenRegistrationReceiveModel.parent_email,
                        password = childrenRegistrationReceiveModel.password,
                        first_name = childrenRegistrationReceiveModel.first_name,
                        last_name = childrenRegistrationReceiveModel.last_name,
                        age = childrenRegistrationReceiveModel.age
                    )
                )
            } catch(e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "User already exist")
            }

            Tokens.insert(
                tokenDTO(
                    user_id = id,
                    token = token
                )
            )

            call.respond(ChildrenRegistrationResponceModel(token = token))
        }
    }
}