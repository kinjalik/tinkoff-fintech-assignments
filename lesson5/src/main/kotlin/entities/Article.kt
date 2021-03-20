package entities

data class Article (
    private val id: Int,
    private val name: String,
    private val text: String,
    private val publicationTime: String,
    private val authorId: Int,
)
