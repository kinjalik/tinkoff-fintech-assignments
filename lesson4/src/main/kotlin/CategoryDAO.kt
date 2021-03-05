class CategoryDAO {
    private val data = listOf<Category>(
        Category(0, "Category 1", "Very cool category!"),
        Category(1, "Category 2", "Very nice category!"),
        Category(2, "Category 3", "Very wonderful category!"),
    )

    fun selectAll() = data
    fun selectById(id: Int) = data[id]
}