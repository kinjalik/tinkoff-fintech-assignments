import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

class ArticleService {
    val data = listOf(
        Article(0, 0, "Title 0", "Blah-blah-blah-0"),
        Article(1, 1, "Title 1", "Blah-blah-blah-1"),
        Article(2, 2, "Title 2", "Blah-blah-blah-2"),
        Article(3, 0, "Title 3", "Blah-blah-blah-3"),
        Article(4, 1, "Title 4", "Blah-blah-blah-4"),
        Article(5, 2, "Title 5", "Blah-blah-blah-5"),
        Article(6, 0, "Title 6", "Blah-blah-blah-6"),
    )
    var articleCount = data.size


    val subscriptions = mutableMapOf<Int, MutableList<Channel<Article>>>()

    suspend fun getByAuthorId(id: Int): List<Article> {
        delay(3000L)
        return data.filter { it.authorId == id }
    }

    fun subscribe(id: Int): Channel<Article> {
        val channel = Channel<Article>()
        if (!subscriptions.containsKey(id))
            subscriptions[id] = mutableListOf(channel)
        else
            subscriptions[id]?.add(channel)
        return channel
    }


    suspend fun startNewsGenerator() {
        println("Generator started")
        while (true) {
            subscriptions.forEach { (authorId, channels) ->
                val articleId = articleCount
                articleCount++
                val article =
                    Article(articleId, authorId, "Title of article $articleId", "Blah-blah-blah text of $articleId")
                println("Generated: $article")
                for (channel in channels) {
                    runBlocking { channel.send(article) }
                }
            }
            delay(3000L)
        }
    }
}

data class Article(
    val id: Int,
    val authorId: Int,
    val title: String,
    val text: String
)