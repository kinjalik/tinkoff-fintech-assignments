class Fetcher(productDAO: ProductDAO, private val categoryDAO: CategoryDAO) {

    private val data: List<Product> = productDAO.selectAll().map {
        Product(it.name, it.description, it.price, it.categoryIds.map { id -> categoryDAO.selectById(id) })
    }

    fun getData() = data

    fun getSortedByPrice() = data.sortedBy { it.price }

    fun getByCategory(category: Category): List<Product> = data.filter { category in it.categories }

    fun getByCategoryId(id: Int): List<Product> = getByCategory(categoryDAO.selectById(id))

    fun countByPredicate(lambda: (Product) -> Boolean) = data.filter(lambda).size
}