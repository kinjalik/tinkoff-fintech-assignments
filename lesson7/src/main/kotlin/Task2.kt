import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.CopyOnWriteArraySet
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

fun main() {
    val test = AtomicInteger(10)
    val observable = Observable(test)


    repeat(3) { i ->
        val t = object : AbstractEventLoop() {
            override fun observerListener() {
                observable.invoke {
                    updates.add(get())
                }
            }
        }
        observable.addListener(t)
        Thread(t).also {
            it.name = "LISTENER-$i"
            it.start()
        }
    }

    thread (name = "UPDATER") {
        for (i in 0..10) {
            println("[${Thread.currentThread().name}] Updating the value. New value: ${i * 10}")
            observable.invokeWithNotify { set(i * 10) }
        }
    }

}

abstract class AbstractEventLoop: Observer, Runnable {
    val updates = ConcurrentLinkedQueue<Int>()

    override fun run() {
        println("[${Thread.currentThread().name}] Thread started")
        while (true) {
            if (updates.isNotEmpty())
                println("[${Thread.currentThread().name}] Updated the value! New value: ${updates.poll()}")
        }
    }

}

class Observable<T> (
    val content: T
) {
    private val listeners = CopyOnWriteArraySet<Observer>()
    fun addListener(t: Observer) = listeners.add(t)

    fun invokeWithNotify(task: T.() -> Unit) {
        synchronized(content!!) {
            content.task()
            for (listener in listeners)
                listener.observerListener()
        }
    }
    fun invoke(task: T.() -> Unit) {
        content.task()
    }
}

interface Observer {
    fun observerListener()
}