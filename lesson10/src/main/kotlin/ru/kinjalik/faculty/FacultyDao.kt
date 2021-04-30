package ru.kinjalik.faculty

import io.ktor.features.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class FacultyDao(private val database: Database) {
    fun getAll() = transaction(database) {
        FacultySchema.selectAll().map(::extractFaculty)
    }

    fun getById(id: Int) = transaction(database) {
        FacultySchema.select { FacultySchema.id.eq(id) }.map(::extractFaculty).first()
    }

    fun add(name: String, foundedDate: String) = transaction(database) {
        val id = FacultySchema.insertAndGetId {
            it[FacultySchema.name] = name
            it[FacultySchema.foundedDate] = foundedDate
        }
        Faculty(id.value, name, foundedDate)
    }

    private fun extractFaculty(row: ResultRow): Faculty = Faculty(
        row[FacultySchema.id].value,
        row[FacultySchema.name],
        row[FacultySchema.foundedDate]
    )
}