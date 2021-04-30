package ru.kinjalik.student

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class StudentDao(private val database: Database) {
    fun getAll() = transaction(database) {
        StudentSchema.selectAll().map(::extractStudent)
    }

    fun getById(id: Int) = transaction(database) {
        StudentSchema.select { StudentSchema.id.eq(id) }.map(::extractStudent).first()
    }

    fun getByFacultyId(fid: Int) = transaction(database) {
        StudentSchema.select { StudentSchema.facultyId eq fid }.map(::extractStudent)
    }

    fun add(name: String, surname: String) = transaction(database) {
        val id = StudentSchema.insertAndGetId {
            it[StudentSchema.name] = name
            it[StudentSchema.surname] = surname
        }
        Student(id.value, name, surname, null)
    }

    fun setGroup(id: Int, facultyId: Int) = transaction(database) {
        StudentSchema.update ({ StudentSchema.id eq id}) {
            it[StudentSchema.facultyId] = facultyId
        }
    }

    private fun extractStudent(row: ResultRow): Student = Student(
        row[StudentSchema.id].value,
        row[StudentSchema.name],
        row[StudentSchema.surname],
        row[StudentSchema.facultyId]
    )
}