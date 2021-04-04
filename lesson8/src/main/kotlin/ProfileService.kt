import kotlinx.coroutines.*

class ProfileService {
    private val data = listOf(
        Profile(0, "Ivan", "Ivanov", "i.ivanov@kinjalik.ru"),
        Profile(1, "Mikhail", "Mikhailov", "m.mikhailov@kinjalik.ru"),
        Profile(2, "Makar", "Makarov", "m.makarov@kinjalik.ru"),
        Profile(3, "Aleksey", "Alekseev", "a.alekseev@kinjalik.ru"),
        Profile(4, "Andrey", "Andreev", "a.andreev@kinjalik.ru"),
        Profile(5, "Ildar", "Ildarov", "i.ildarov@kinjalik.ru"),
    )

    suspend fun getById(id: Int): Profile? {
        delay(100L)
        return data.filter { it.id == id }[0]
    }
}

data class Profile(
    val id: Int,
    val name: String,
    val surname: String,
    val email: String,
)