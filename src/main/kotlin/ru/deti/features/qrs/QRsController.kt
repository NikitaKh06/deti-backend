package ru.deti.features.qrs

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.deti.database.childrens.Childrens
import ru.deti.database.qrs.Qrs
import ru.deti.database.qrs.qrsInsertDTO
import ru.deti.database.tokens.Tokens

class QRsController {

    suspend fun insertQR(call: ApplicationCall) {
        val qRsInsertReceiveModel = call.receive<QRsInsertReceiveModel>()
        val childrenModel = Childrens.fetchChildren(qRsInsertReceiveModel.parent_email)
        if (childrenModel != null) {
            Qrs.insert(
                qrsInsertDTO(
                    user_id = childrenModel.id,
                    qr_code = qRsInsertReceiveModel.qr_code
                )
            )
            call.respond(HttpStatusCode.OK)
        }
        else{
            call.respond(HttpStatusCode.Conflict, "Children doest exist")
        }
    }

    suspend fun fetchQR(call: ApplicationCall) {
        val qRsFetchReceiveModel = call.receive<QRsFetchReceiveModel>()
        val childrenModel = Tokens.fetchUser(qRsFetchReceiveModel.token)

        if(childrenModel != null) {
            val qrModel = Qrs.fetchQR(childrenModel.user_id)

            if (qrModel != null) {
                call.respond(QRsResponceModel(qr_code = qrModel.qr_code))
            }
            else {
                call.respond(HttpStatusCode.Conflict, "QR doesn't exist")
            }
        }
        else {
            call.respond(HttpStatusCode.Conflict, "User with this token doesn't exist")
        }
    }
}