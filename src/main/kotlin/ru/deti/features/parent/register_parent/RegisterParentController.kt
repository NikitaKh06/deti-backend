package ru.deti.features.parent.register_parent

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import ru.deti.database.parents.Parents
import ru.deti.database.parents.parentRegisterDTO
import ru.deti.database.tokens.Tokens
import ru.deti.database.tokens.tokenDTO
import ru.deti.utils.isValidEmail
import kotlin.random.Random
import java.util.UUID

class RegisterParentController () {

    suspend fun registerNewParent(call: ApplicationCall) {
        val parentRegistrationReceiveModel = call.receive<ParentRegistrationReceiveModel>()

        if(!parentRegistrationReceiveModel.email.isValidEmail()) {
            call.respond(HttpStatusCode.Conflict, "Email is not valid")
        }

        val parentRegisterDTO =  Parents.fetchParent(parentRegistrationReceiveModel.email)

        if(parentRegisterDTO != null) {
            call.respond(HttpStatusCode.Conflict, "Parent already exist")
        }
        else {
            val token = UUID.randomUUID().toString()
            val id = Random.nextInt().toString()
            try {
                Parents.insert (
                    parentRegisterDTO(
                        id = id,
                        email = parentRegistrationReceiveModel.email,
                        password = parentRegistrationReceiveModel.password,
                        first_name = parentRegistrationReceiveModel.first_name,
                        last_name = parentRegistrationReceiveModel.last_name,
                        children_id = ""
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

            call.respond(ParentRegistrationResponceModel(token = token))
        }
    }
}