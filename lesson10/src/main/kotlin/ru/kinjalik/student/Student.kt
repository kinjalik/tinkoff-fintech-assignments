package ru.kinjalik.student

import kotlinx.serialization.Serializable

@Serializable
data class Student (
    val id: Int,
    val name: String,
    val surname: String,
    val faculty_id: Int?
)