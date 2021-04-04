import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.CopyOnWriteArraySet
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

fun main() {
    val test = VolatileInteger()

    thread {
        for (i in 0..10) {
            test.act { this + 1 }
            println("[${Thread.currentThread().name}] Incremented value: ${test.value}.")
        }
    }

    for (i in 1..3) {
        thread {
            var cur = test.value
            while (true) {

                if (test.value != cur) {
                    cur = test.value
                    println("[${Thread.currentThread().name}] New value: $cur.")
                }
            }
        }
    }

}

class VolatileInteger {
    @Volatile
    var value: Int = 10
        private set

    fun act(lambda: Int.() -> Int) {
        value = value.lambda()
    }

    override fun toString(): String {
        return value.toString()
    }
}