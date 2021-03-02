import java.util.*

/**
 * A LinkedList-based implementation of Queue ADT.
 * @param T the type of stored values
 */
class Queue<T> : Collection<T> {
    private var head: ListItem<T>? = null
    private var tail: ListItem<T>? = null
    override var size: Int = 0

    /**
     * Places the value in the end of queue
     * @param item value to store
     */
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

    /**
     * Takes the value from front and returns it
     * @return the value from fron of queue
     */
    fun dequeue(): T? {
        if (size == 0) {
            throw EmptyStructureException("Can not dequeue empty queue.")
        }

        val res = head?.getValue()
        head = head?.getNext()
        if (res != null) size--
        return res
    }

    /**
     * Returns `true` if `element` stored in queue
     * @param element value to check
     * @return boolean indicator of containment
     */
    override fun contains(element: T): Boolean {
        var cur = head
        while (cur != null && cur.getValue() != element) {
            cur = cur.getNext()
        }

        return cur != null

    }

    /**
     * Returns `true` if queue is empty
     * @return boolean indicator of emptiness
     */
    override fun isEmpty(): Boolean = size == 0

    /**
     * Returns an iterator over the queue
     * @return iterator object
     */
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
            if (!contains(el))
                return false
        return true
    }
}