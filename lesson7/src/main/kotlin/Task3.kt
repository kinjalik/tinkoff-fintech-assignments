import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun main() {
    val results = mutableListOf<Pair<Long, Int>>()
    val poolSizes = listOf(10, 20, 30)

    for (size in poolSizes) {
        val pool = Executors.newFixedThreadPool(size)

        val start = System.nanoTime()
        var t = 0

        repeat(size) {
            pool.submit {
                synchronized(t) {
                    while (t < 1_000_000)
                        t++
                }
            }
        }
        pool.shutdown()
        val end = System.nanoTime()

        results.add(Pair<Long, Int>(end - start, size))
    }
    results.sortBy { it.first }
    for (res in results) {
        println("Pool of size ${res.second} with result ${TimeUnit.MILLISECONDS.convert(res.first, TimeUnit.NANOSECONDS)} ms")
    }
}