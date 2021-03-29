import kotlin.concurrent.thread
import kotlin.random.Random

fun main() {
    val curThreads = mutableListOf<Thread>()

    // Thread
    repeat(5) {
        curThreads.add(Thread { println("I'm Thread ${Thread.currentThread().name}" )}.also { it.start() })
    }
    curThreads.forEach { it.join() }

    Thread.sleep(3000)

    // Runnable
    curThreads.clear()
    repeat(5) {
        curThreads.add(Thread(Task()).also { it.start() })
    }
    curThreads.forEach { it.join() }

    Thread.sleep(3000)

    // DSL Thread
    curThreads.clear()
    repeat(5) {
        curThreads.add(thread { println("I'm thread from DSL ${Thread.currentThread().name}") })
    }
    curThreads.forEach { it.join() }

    Thread.sleep(3000)

    // Priority Thread Demo -- no guarantees
    curThreads.clear()
    repeat(50) {
        val t = Random(it).nextInt(Thread.MIN_PRIORITY, Thread.MAX_PRIORITY)
        curThreads.add(thread(priority = t) {
            println("I'm ${Thread.currentThread().name} with priority ${Thread.currentThread().priority}")
        })
    }
    curThreads.forEach { it.join() }

    // Daemon Thread
    val t = thread(isDaemon = true, name = "DAEMON") {
        while (true) {
            println("${Thread.currentThread().name}: I won't prevent the end of program!")
            Thread.sleep(1000)
        }
    }
    thread(name = "BLOCKING THREAD") {
        println("${Thread.currentThread().name}: I will prevent the end of program!")
        Thread.sleep(5000)
        println("${Thread.currentThread().name}: Now I'm dying")
    }
}

class Task : Runnable {
    override fun run() {
        println("I'm thread from Runnable ${Thread.currentThread().name}")
    }

}