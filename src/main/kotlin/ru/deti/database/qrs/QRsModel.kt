package ru.deti.database.qrs

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import ru.deti.features.qrs.QRsResponceModel

object Qrs : Table() {
    private val user_id = Qrs.varchar("user_id", 50)
    private val qr_code = Qrs.varchar("qr_code", 50)

    fun insert(qrsInsertDTO: qrsInsertDTO) {
        transaction {
            Qrs.insert {
                it[user_id] = qrsInsertDTO.user_id
                it[qr_code] = qrsInsertDTO.qr_code
            }
        }
    }

    fun fetchQR(children_id: String) : QRsResponceModel? {
        return try {
            transaction {
                val qrModel = Qrs.select { Qrs.user_id.eq( children_id ) }.single()
                QRsResponceModel(
                    qr_code = qrModel[qr_code]
                )
            }
        } catch (e: Exception) { null }
    }
}