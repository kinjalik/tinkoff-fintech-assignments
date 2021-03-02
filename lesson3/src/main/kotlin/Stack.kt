class Stack<T> : Collection<T> {
    override var size: Int = 0
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

    override fun contains(element: T): Boolean {
        var cur = head
        while (cur != null && cur.getValue() != element)
            cur = cur.getNext()

        return cur != null
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        for (el in elements)
            if (contains(el))
                return true
        return false
    }

    override fun isEmpty(): Boolean = size == 0

    override fun iterator(): Iterator<T> = object : Iterator<T> {
        var next = head
        override fun hasNext(): Boolean {
            return next != null
        }

        override fun next(): T {
            val t = next
            next = next?.getNext()
            return t!!.getValue()
        }

    }
}