package ru.deti.database.subjects

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object Subjects : Table() {
    private val id = Subjects.varchar("id", 50)
    private val title = Subjects.varchar("title", 25)
    private val day = Subjects.varchar("day", 15)
    private val hours = Subjects.varchar("hours", 2)
    private val minutes = Subjects.varchar("minutes", 2)
    private val children_id = Subjects.varchar("children_id", 50)
    private val homework = Subjects.varchar("homework", 100)
    private val comment = Subjects.varchar("comment", 100)

    fun insert(subjectDTO: subjectDTO) {
        transaction {
            Subjects.insert {
                it[id] = subjectDTO.id
                it[title] = subjectDTO.title
                it[day] = subjectDTO.day
                it[hours] = subjectDTO.hours
                it[minutes] = subjectDTO.minutes
                it[children_id] = subjectDTO.children_id
                it[homework] = subjectDTO.homework
                it[comment] = subjectDTO.comment
            }
        }
    }

    fun fetchSubject(subject_id: String) : subjectDTO? {
        return try {
            transaction {
                val subjectModel = Subjects.select { Subjects.id eq subject_id }.single()
                subjectDTO(
                    id = subjectModel[Subjects.id],
                    title = subjectModel[Subjects.title],
                    day = subjectModel[Subjects.day],
                    hours = subjectModel[Subjects.hours],
                    minutes = subjectModel[minutes],
                    children_id = subjectModel[children_id],
                    homework = subjectModel[homework],
                    comment = subjectModel[comment]
                )
            }
        } catch (e: Exception) { null }
    }

    fun fetchSubjectToArray(children_id: String, day: String) : MutableList<subjectRespondArrayDTO>? {
        return try {
            transaction {
                val subjects = mutableListOf<subjectRespondArrayDTO>()
                Subjects.select {
                    Subjects.children_id.eq(children_id) and Subjects.day.eq(day)
                }.forEach {
                    subjects.add(
                        subjectRespondArrayDTO(
                            id = it[Subjects.id],
                            title = it[title],
                            day = it[Subjects.day],
                            hours = it[hours],
                            minutes = it[minutes]
                        )
                    )
                }
                subjects
            }
        } catch (e: Exception) { null }
    }

    fun addComment(subject_id: String, text: String) {
        transaction {
            Subjects.update ( { Subjects.id eq subject_id } ) {
                it[comment] = text
            }
        }
    }

    fun addHomework(subject_id: String, text: String) {
        transaction {
            Subjects.update ( { Subjects.id eq subject_id } ) {
                it[homework] = text
            }
        }
    }
}