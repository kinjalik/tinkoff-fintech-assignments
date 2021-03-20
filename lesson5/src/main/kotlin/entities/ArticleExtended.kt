package entities

data class ArticleExtended (
    private val id: Int,
    private val name: String,
    private val text: String,
    private val publicationTime: String,
    private val authorId: Int,
    private val authorName: String,
    private val authorSurname: String
)