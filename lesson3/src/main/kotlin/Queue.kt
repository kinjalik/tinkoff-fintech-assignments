class Queue<T> {
    private var head: ListItem<T>? = null
    private var tail: ListItem<T>? = null
    private var size: Int = 0

    fun enqueue(item: T) {
        if (size == 0) {
            head = ListItem(item)
            tail = head
        } else {
            val newTail = ListItem(item)
            tail?.setNext(newTail)
            tail = newTail
        }
        size++
    }

    fun dequeue(): T? {
        val res = head?.getValue()
        head = head?.getNext()
        if (res != null) size--
        return res
    }

    fun demoPrint() {
        if (size == 0) {
            println("Trying to demoPrint empty queue")
            return
        }
        println("Current state of queue:")
        var i = 0
        var cur = head
        while (cur != null) {
            println("Item $i: ${cur.getValue()}")
            cur = cur.getNext()
            i++
        }
        println("End of current state of queue")
    }
}