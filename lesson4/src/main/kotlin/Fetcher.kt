class Fetcher(productDAO: ProductDAO, private val categoryDAO: CategoryDAO) {

    private val data: List<Product> = productDAO.selectAll().map {
        Product(it.id, it.name, it.description, it.price, it.categoryIds.mapNotNull { id -> categoryDAO.selectById(id) })
    }

    fun getData() = data
    fun getProductById(id: Int): Product? = data.find {it.id == id}

    fun getSortedByPrice() = data.sortedBy { it.price }

    fun countByPredicate(lambda: (Product) -> Boolean) = data.filter(lambda).size

    fun getGroupedByCategory(): Map<Category, List<Product>> {
        return data.flatMap { prod ->
            prod.categories.map { cat -> Pair(prod.id, cat) }
        }.groupBy { it.second }.entries.associate { entry -> entry.key to entry.value.mapNotNull { getProductById(it.first) } }
    }
}