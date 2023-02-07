package ru.deti.database.tokens

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Tokens : Table() {
    private val user_id = Tokens.varchar("user_id", 50)
    private val token = Tokens.varchar("token", 50)

    fun insert(tokenDTO: tokenDTO) {
        transaction {
            Tokens.insert {
                it[user_id] = tokenDTO.user_id
                it[token] = tokenDTO.token
            }
        }
    }

    fun fetchUser(token: String): FetchUserResponceDTO? {
        return try {
            transaction {
                val userModels = Tokens.select{ Tokens.token.eq(token) }.single()
                FetchUserResponceDTO(
                    user_id = userModels[Tokens.user_id]
                )
            }
        } catch (e: Exception) { null }
    }

    // Сделать удаление записи по id
    fun deleteToken(user_id: String) {
        transaction {
            Tokens.deleteWhere { Tokens.user_id eq user_id }
        }
    }
}