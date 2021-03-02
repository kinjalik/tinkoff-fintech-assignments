fun main() {
    val numberOfTestItems = 3
    println("Queue demonstration")
    var queueExample = Queue<String>()

    println("Current size: ${queueExample.size}")
    for (i in 1..numberOfTestItems)
        queueExample.enqueue("Item #$i")
    println("Current size: ${queueExample.size}")

    println("Demo of iterator:")
    for (el in queueExample)
        println(el)

    println("Demo of contains:")
    println("\"Item #1\" in queue: ${queueExample.contains("Item #1")}")
    println("\"Item #1337\" in queue: ${queueExample.contains("Item #1337")}")


    println("Demo of dequeue:")
    for (i in 0..numberOfTestItems)
        println(queueExample.dequeue())
    println("Current size: ${queueExample.size}")

    println("----------------------------------")
    println("Stack demonstration")
    var stackExample = Stack<String>()

    println("Current size: ${stackExample.size}")
    for (i in 1..numberOfTestItems)
        stackExample.push("Item #$i")
    println("Current size: ${stackExample.size}")

    println("Demo of iterator:")
    for (el in stackExample)
        println(el)

    println("Demo of contains:")
    println("\"Item #1\" in stack: ${stackExample.contains("Item #1")}")
    println("\"Item #1337\" in stack: ${stackExample.contains("Item #1337")}")

    println("Demo of pop:")
    for (i in 0..numberOfTestItems)
        println(stackExample.pop())
    println("Current size: ${stackExample.size}")

    println("----------------------------------")
    println("DLS on queue demo")
    queueExample = queueOf("first", "second", "third")
    for (e in queueExample)
        println(e)

    println("----------------------------------")
    println("DLS on Stack demo")
    stackExample = stackOf("first", "second", "third")
    for (e in stackExample)
        println(e)
}

