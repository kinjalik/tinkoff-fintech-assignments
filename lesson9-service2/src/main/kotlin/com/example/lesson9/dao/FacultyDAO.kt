package com.example.lesson9.dao

import com.example.lesson9.model.Faculty

class FacultyDAO {
    private val data = listOf(
        Faculty(1, "Faculty I", 1337),
        Faculty(2, "Faculty II", 322),
        Faculty(3, "Faculty III", 228),
        Faculty(4, "Faculty IV", 2002),
    )
    fun getAll() = data
    fun getById(id: Int): Faculty? = data.filter { it.id == id }.firstOrNull()
}