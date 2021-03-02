fun main() {
    val numberOfTestItems = 3
    println("Queue demonstration")
    val q = Queue<String>()

    q.demoPrint()
    for (i in 1..numberOfTestItems)
        q.enqueue("Item #$i")

    q.demoPrint()

    for (i in 0..numberOfTestItems)
        println(q.dequeue())
    q.demoPrint()

    println("----------------------------------")
    println("Stack demonstration")
    val s = Stack<String>()

    s.demoPrint()
    for (i in 1..numberOfTestItems)
        s.push("Item #$i")

    s.demoPrint()

    for (i in 0..numberOfTestItems)
        println(s.pop())
    s.demoPrint()
}
