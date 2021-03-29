package entities

data class User(
    private val id: Int,
    val name: String,
    val surname: String,
    val regDate: String
)