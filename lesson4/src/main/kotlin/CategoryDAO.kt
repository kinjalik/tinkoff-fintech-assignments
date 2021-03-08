class CategoryDAO {
    private val data = listOf(
        Category(0, "Category 1", "Very cool category!"),
        Category(1, "Category 2", "Very nice category!"),
        Category(2, "Category 3", "Very wonderful category!"),
    )

    fun selectAll() = data
    fun selectById(id: Int): Category? = data.find { it.id == id }.also {
        if (it == null)
            System.err.println("Not found category with id $id")
    }
}