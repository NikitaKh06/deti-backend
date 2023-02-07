package ru.deti.database.childrens

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Childrens : Table() {
    private val id = Childrens.varchar("id", 50)
    private val parent_email = Childrens.varchar("parent_email", 25)
    private val password = Childrens.varchar("password", 20)
    private val first_name = Childrens.varchar("first_name", 20)
    private val last_name = Childrens.varchar("last_name", 20)
    private val age = Childrens.varchar("age", 5)

    fun insert(childrenRegisterDTO: childrenRegisterDTO) {
        transaction {
            Childrens.insert {
                it[id] = childrenRegisterDTO.id
                it[parent_email] = childrenRegisterDTO.parent_email
                it[password] = childrenRegisterDTO.password
                it[first_name] = childrenRegisterDTO.first_name
                it[last_name] = childrenRegisterDTO.last_name
                it[age] = childrenRegisterDTO.age
            }
        }
    }

    fun fetchChildren(parent_email: String) : childrenRegisterDTO? {
        return try {
            transaction {
                val childrenModel = Childrens.select{ Childrens.parent_email.eq(parent_email) }.single()
                childrenRegisterDTO(
                    id = childrenModel[Childrens.id],
                    parent_email = childrenModel[Childrens.parent_email],
                    password = childrenModel[Childrens.password],
                    first_name = childrenModel[Childrens.first_name],
                    last_name = childrenModel[Childrens.last_name],
                    age = childrenModel[Childrens.age]
                )
            }
        } catch (e: Exception) { null }
    }

    fun fetchChildrenWithId(childrenId: String) : childrenRegisterDTO? {
        return try {
            transaction {
                val childrenModel = Childrens.select{ Childrens.id.eq(childrenId) }.single()
                childrenRegisterDTO(
                    id = childrenModel[Childrens.id],
                    parent_email = childrenModel[Childrens.parent_email],
                    password = childrenModel[Childrens.password],
                    first_name = childrenModel[Childrens.first_name],
                    last_name = childrenModel[Childrens.last_name],
                    age = childrenModel[Childrens.age]
                )
            }
        } catch (e: Exception) { null }
    }
}