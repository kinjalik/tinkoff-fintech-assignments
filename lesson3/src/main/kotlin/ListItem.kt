/**
 * A container for item of LinkedList-based structures
 *
 * @param T the type of value stored in container
 * @property item the value stored in container
 */
class ListItem<T>(private val item: T) {
    private var nextItem: ListItem<T>? = null

    /**
     * Getter for stored value
     * @return stored value
     */
    fun getValue() = item

    /**
     * Getter for next linked container (due to the java/kotlin referencing nature)
     * @return reference for next linked container
     */
    fun getNext() = nextItem

    /**
     * Setter for next linked container
     */
    fun setNext(next: ListItem<T>?) {
        nextItem = next
    }
}