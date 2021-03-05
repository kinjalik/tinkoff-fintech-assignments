fun main() {
    val fetcher = Fetcher(ProductDAO(), CategoryDAO())

    println("#### FETCHING EVERYTHING ####")
    fetcher.getData().onEach(::println)

    println("#### FETCHING SORTED (by price) ####")
    fetcher.getSortedByPrice().onEach(::println)

    println("#### FETCHING GROUPED (by category id 2) ####")
    fetcher.getByCategoryId(2).onEach(::println)

    println("#### FETCHING ELEMENT COUNT ####")
    fetcher.countByPredicate { it.price < 200 }.also(::println)

}