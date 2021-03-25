class Notebook (
    var title: String,
    var description: String,
    var capacity: Int
) {
    val notes: MutableList<String> = mutableListOf<String>()

    fun add(note: String): Boolean {
        if (notes.size == capacity)
            return false
        notes.add(note)
        return true
    }
}

fun phonebook(block: Notebook.() -> Unit): Notebook {
    return Notebook("", "", 0).also(block)}