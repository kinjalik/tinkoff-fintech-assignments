class Stack<T> {
    private var size: Int = 0
    private var head: ListItem<T>? = null

    fun push(item: T) {
        head = ListItem(item).also { it.setNext(head) }
        size++
    }

    fun pop(): T? {
        val t = head?.getValue()
        head = head?.getNext()
        if (t != null) size--
        return t
    }

    fun demoPrint() {
        if (size == 0) {
            println("Trying to demoPrint empty stack")
            return
        }
        println("Current state of stack:")
        var i = 0
        var cur = head
        while (cur != null) {
            println("Item $i: ${cur.getValue()}")
            cur = cur.getNext()
        }
        println("End of current state of stack")
    }
}