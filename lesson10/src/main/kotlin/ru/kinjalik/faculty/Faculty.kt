package ru.kinjalik.faculty

import kotlinx.serialization.Serializable

@Serializable
data class Faculty (
    val id: Int,
    val name: String,
    val foundedDate: String
)