package com.example.lesson9.model

data class StudentRaw(
    val id: Int,
    val name: String,
    val surname: String,
    val birthday: String,
    val enrollmentYear: Int,
    val facultyId: Int
)