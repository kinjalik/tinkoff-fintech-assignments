package ru.kinjalik.faculty

import org.jetbrains.exposed.dao.id.IntIdTable

object FacultySchema : IntIdTable(name = "faculties", columnName = "id") {
    val name = text("name")
    val foundedDate = text("FOUNDEDDATE")
}