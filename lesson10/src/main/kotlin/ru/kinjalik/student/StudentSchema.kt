package ru.kinjalik.student

import org.jetbrains.exposed.dao.id.IntIdTable

object StudentSchema : IntIdTable(name = "students", columnName = "id") {
    val name = text("name")
    val surname = text("surname")
    val facultyId = integer("faculty_id")
}