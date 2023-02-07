package ru.deti.database.parents

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

object Parents : Table() {
    private val id = Parents.varchar("id", 50)
    private val email = Parents.varchar("email", 25)
    private val password = Parents.varchar("password", 20)
    private val first_name = Parents.varchar("first_name", 20)
    private val last_name = Parents.varchar("last_name", 20)
    private val children_id = Parents.varchar("children_id", 50)

    fun insert(parentRegisterDTO: parentRegisterDTO) {
        transaction {
            Parents.insert {
                it[id] = parentRegisterDTO.id
                it[email] = parentRegisterDTO.email
                it[password] = parentRegisterDTO.password
                it[first_name] = parentRegisterDTO.first_name
                it[last_name] = parentRegisterDTO.last_name
                it[children_id] = ""
            }
        }
    }

    fun fetchParent(email: String) : parentRegisterDTO? {
        return try {
            transaction {
                val parentModel = Parents.select{ Parents.email.eq(email) }.single()
                parentRegisterDTO(
                    id = parentModel[Parents.id],
                    email = parentModel[Parents.email],
                    password = parentModel[Parents.password],
                    first_name = parentModel[Parents.first_name],
                    last_name = parentModel[Parents.last_name],
                    children_id = parentModel[Parents.children_id]
                )
            }
        } catch (e: Exception) { null }
    }

    fun fetchParentWithId(parentId: String) : parentRegisterDTO? {
        return try {
            transaction {
                val parentModel = Parents.select{ Parents.id.eq(parentId) }.single()
                parentRegisterDTO(
                    id = parentModel[Parents.id],
                    email = parentModel[Parents.email],
                    password = parentModel[Parents.password],
                    first_name = parentModel[Parents.first_name],
                    last_name = parentModel[Parents.last_name],
                    children_id = parentModel[Parents.children_id]
                )
            }
        } catch (e: Exception) { null }
    }

    fun addChildren(childrenId: String, parentId: String) {
        transaction {
            Parents.update( {Parents.id eq parentId} ) {
                it[children_id] = childrenId
            }
        }
    }
}