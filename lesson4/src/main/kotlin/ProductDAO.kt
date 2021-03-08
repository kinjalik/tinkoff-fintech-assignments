class ProductDAO {
    private val data = listOf(
        ProductRaw(0, "Product 1", "Very cool product!", 324, listOf(0, 1)),
        ProductRaw(1, "Product 2", "Very nice product!", 424, listOf(1, 2)),
        ProductRaw(2, "Product 3", "Very wonderful product!", 853, listOf(0, 2)),
        ProductRaw(3, "Product 4", "Very simple product!", 421, listOf(0)),
        ProductRaw(4, "Product 5", "Very complex product!", 853, listOf(1)),
        ProductRaw(5, "Product 6", "Very useful product!", 658, listOf(2)),
        ProductRaw(6, "Product 7", "Very good product!", 102, listOf(0, 1, 2))
    )

    fun selectAll() = data
    fun selectById(id: Int) = data.find { it.id == id }
}