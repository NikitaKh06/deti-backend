package ru.deti.database.photos

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Photos : Table() {
    private val user_id = Photos.varchar("user_id", 50)
    private val photo = Photos.varchar("photo", 50)

    fun insert(photosDTO: photosDTO) {
        transaction {
            Photos.insert {
                it[user_id] = photosDTO.user_id
                it[photo] = photosDTO.photo
            }
        }
    }

    fun fetchPhoto(user_id: String) : photosDTO? {
        return try {
            transaction {
                val photoModel = Photos.select{ Photos.user_id.eq(user_id) }.single()
                photosDTO(
                    user_id = photoModel[Photos.user_id],
                    photo = photoModel[Photos.photo]
                )
            }
        } catch (e: Exception) { null }
    }
}