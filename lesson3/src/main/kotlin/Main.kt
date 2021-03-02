fun main() {
    val numberOfTestItems = 3
    println("Queue demonstration")
    val q = Queue<String>()

    println("Current size: ${q.size}")
    for (i in 1..numberOfTestItems)
        q.enqueue("Item #$i")
    println("Current size: ${q.size}")

    println("Demo of iterator:")
    for (el in q)
        println(el)

    println("Demo of contains:")
    println("\"Item #1\" in queue: ${q.contains("Item #1")}")
    println("\"Item #1337\" in queue: ${q.contains("Item #1337")}")


    println("Demo of dequeue:")
    for (i in 0..numberOfTestItems)
        println(q.dequeue())
    println("Current size: ${q.size}")

    println("----------------------------------")
    println("Stack demonstration")
    val s = Stack<String>()

    println("Current size: ${s.size}")
    for (i in 1..numberOfTestItems)
        s.push("Item #$i")
    println("Current size: ${s.size}")

    println("Demo of iterator:")
    for (el in s)
        println(el)

    println("Demo of contains:")
    println("\"Item #1\" in stack: ${s.contains("Item #1")}")
    println("\"Item #1337\" in stack: ${s.contains("Item #1337")}")

    println("Demo of pop:")
    for (i in 0..numberOfTestItems)
        println(s.pop())
    println("Current size: ${s.size}")
}
