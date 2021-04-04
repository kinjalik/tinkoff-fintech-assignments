import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit
import kotlin.random.Random

fun main() = runBlocking {
    val profileService = ProfileService()
    val articleService = ArticleService()
    val testId = 2

    val startTime = System.nanoTime()

    // Make requests
    val profileDeferred = async { profileService.getById(testId) }
    val articlesDeferred = async { articleService.getByAuthorId(testId) }

    // Await results
    val profile = profileDeferred.await() // 100 ms for answer
    val articles = articlesDeferred.await() // 3000 ms for answer
    if (profile != null) {
        println("User #${profile.id}: ${profile.name} ${profile.surname} (${profile.email}")
        for (article in articles)
            println("\"${article.title}\" (${article.id}): ${article.text}")
    } else {
        println("No user with id $testId")
    }

    val endTime = System.nanoTime()
    val execTime = TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS)

    println("Everything took $execTime ms")
}