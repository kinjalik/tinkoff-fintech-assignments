package com.example.lesson9.dao

import com.example.lesson9.model.StudentRaw

class StudentDao {
    fun getAll(): List<StudentRaw> {
        return listOf(
            StudentRaw(0, "FirstName1", "Surname1", "12.03.1337", 2002, 1),
            StudentRaw(1, "FirstName2", "Surname2", "13.03.1337", 2003, 2),
            StudentRaw(2, "FirstName3", "Surname3", "14.03.1337", 2004, 1),
            StudentRaw(3, "FirstName4", "Surname4", "15.03.1337", 2005, 2),

        )
    }

    fun getById(id: Int): StudentRaw? {
        val res = getAll().filter { it.id == id }
        return if (res.isEmpty())
            null
        else res[0]
    }

    fun exists(id: Int): Boolean {
        return !getAll().none { it.id == id }
    }
}