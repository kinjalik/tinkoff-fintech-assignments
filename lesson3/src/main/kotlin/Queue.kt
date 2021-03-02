class Queue<T> : Collection<T> {
    private var head: ListItem<T>? = null
    private var tail: ListItem<T>? = null
    override var size: Int = 0

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

    override fun contains(element: T): Boolean {
        var cur = head
        while (cur != null && cur.getValue() != element)
            cur = cur.getNext()

        return cur != null

    }

    override fun isEmpty(): Boolean = size == 0
    override fun iterator(): Iterator<T> = object : Iterator<T> {
        var next = head
        override fun hasNext(): Boolean = next != null
        override fun next(): T {
            val t = next
            next = next!!.getNext()
            return t!!.getValue()
        }
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        for (el in elements)
            if (contains(el))
                return true
        return false
    }
}