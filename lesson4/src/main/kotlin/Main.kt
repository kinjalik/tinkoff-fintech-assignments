fun main() {
    val fetcher = Fetcher(ProductDAO(), CategoryDAO())

    println("#### FETCHING EVERYTHING ####")
    fetcher.getData().onEach(::println)

    println("#### FETCHING SORTED (by price) ####")
    fetcher.getSortedByPrice().onEach(::println)

    println("#### FETCHING GROUPED ####")
    fetcher.getGroupedByCategory().forEach { k, v ->
        println("Key $k:")
        println(v)
    }

    println("#### FETCHING ELEMENT COUNT ####")
    fetcher.countByPredicate { it.price < 200 }.also(::println)

}