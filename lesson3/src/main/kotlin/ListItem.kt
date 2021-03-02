class ListItem<T>(private val item: T) {
    private var nextItem: ListItem<T>? = null

    fun getValue() = item
    fun getNext() = nextItem
    fun setNext(next: ListItem<T>?) {
        nextItem = next
    }
}