package ru.deti.database.roads

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Roads : Table() {
    private val subject_id = Roads.varchar("subject_id", 50)
    private val road = Roads.varchar("road", 50)

    fun insert(roadsDTO: roadsDTO) {
        transaction {
            Roads.insert {
                it[subject_id] = roadsDTO.subject_id
                it[road] = roadsDTO.road
            }
        }
    }

    fun fetchRoad(subject_id: String) : roadsDTO?{
        return try {
            transaction {
                val roadModel = Roads.select( Roads.subject_id eq subject_id).single()
                roadsDTO(
                    subject_id = roadModel[Roads.subject_id],
                    road = roadModel[Roads.road]
                )
            }
        } catch (e: Exception) { null }
    }

    fun deleteRoad(subject_id: String) {
        transaction {
            Roads.deleteWhere {
                Roads.subject_id eq subject_id
            }
        }
    }
}