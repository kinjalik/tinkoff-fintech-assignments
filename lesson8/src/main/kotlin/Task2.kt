import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.random.Random

fun main() = runBlocking {
    val articleService = ArticleService()

    launch {
        articleService.startNewsGenerator()
    }

    repeat(3) { getterId ->
        launch {
            val channel = articleService.subscribe(getterId)
            println("Creating the subscription $getterId to $getterId")
            while (true) {
                val newArticle =
                    withContext(Dispatchers.Default) { channel.receive() }
                println("[Getter-$getterId] Received new article from author $getterId: ${newArticle.title}")
            }
        }

    }
}