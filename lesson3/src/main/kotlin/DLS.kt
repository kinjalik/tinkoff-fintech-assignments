fun <T> stackOf(vararg elements: T): Stack<T> = Stack<T>().also { for (el in elements) it.push(el) }

fun <T> queueOf(vararg elements: T): Queue<T> = Queue<T>().also { for (el in elements) it.enqueue(el) }