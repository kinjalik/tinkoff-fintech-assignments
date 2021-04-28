package ru.kinjalik.service2.models

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val quotes: List<Quote>
)
