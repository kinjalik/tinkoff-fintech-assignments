/**
 * A LinkedList-based implementation of Stack ADT.
 * @param T the type of stored values
 */
class Stack<T> : Collection<T> {
    override var size: Int = 0
    private var head: ListItem<T>? = null

    /**
     * Places the value on top of stack
     * @param item the value to store
     */
    fun push(item: T) {
        head = ListItem(item).also { it.setNext(head) }
        size++
    }

    /**
     * Removes the value from top of stack
     * @return the value, that were just removed
     */
    fun pop(): T? {
        if (size == 0) {
            throw EmptyStructureException("Can not pop empty stack.")
        }
        val t = head?.getValue()
        head = head?.getNext()
        if (t != null) size--
        return t
    }

    /**
     * Returns `true` if the value stored in stack
     * @param element the value for check
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
     * Returns `true` if all items of `elements` stored in stack
     * @param elements collection of items for checking
     * @return boolean indicator
     */
    override fun containsAll(elements: Collection<T>): Boolean {
        for (el in elements) {
            if (!contains(el)) {
                return false
            }
        }
        return true
    }

    /**
     * Returns `true` if stack is empty
     * @return boolean indicator of emptiness
     */
    override fun isEmpty(): Boolean = size == 0

    /**
     * Return an iterator over the stack
     * @return iterator object
     */
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