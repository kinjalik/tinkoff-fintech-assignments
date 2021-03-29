package entities

data class Article (
    private val id: Int,
    val name: String,
    val text: String,
    val publicationTime: String,
    val authorId: Int,
)
