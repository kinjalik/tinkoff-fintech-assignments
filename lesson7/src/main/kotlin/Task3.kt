import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

fun main() {
    val results = mutableListOf<Pair<Long, Int>>()
    val poolSizes = listOf(30, 20, 10)

    for (size in poolSizes) {
        val pool = Executors.newFixedThreadPool(size)

        val start = System.nanoTime()
        var t = AtomicInteger(0)

        repeat(size) {
            pool.submit {
                while (t.get() < 1_000_000)
                    t.incrementAndGet()
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