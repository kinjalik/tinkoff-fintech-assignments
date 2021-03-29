import database.DatabaseClient
import database.DatabaseFetcher
import database.DatabaseInitializer
import exceptions.NoConnectionToDatabaseException
import java.sql.SQLException

fun main() {
    val connection = "jdbc:sqlite:database.sqlite"
    val client = DatabaseClient(connection).also { it.connect() }

    val initializer = DatabaseInitializer(client)
    val fetcher = DatabaseFetcher(client)

    /*
    To perform correct tests, please insert data from folder "demodata"
     */

    // Init
    try {
        initializer.createTableUsers()
    } catch (e: SQLException) {
        println("Failed to create table users")
        println(e.message)
    }
    try {
        initializer.createTableArticles()
    } catch (e: SQLException) {
        println("Failed to create table articels")
        println(e.message)
    }
    try {
        initializer.createTableCommunities()
    } catch (e: SQLException) {
        println("Failed to create table communitites")
        println(e.message)
    }
    try {
        initializer.createTableCommunityAdmins()
    } catch (e: SQLException) {
        println("Failed to create table communitityAdmins")
        println(e.message)
    }

    // Get users
    for (i in 1..20) {
        try {
            val user = fetcher.getUserById(i)
            if (user == null) {
                println("User with id $i doesn't exist")
                break
            } else {
                println(user)
            }
        } catch (e: NoConnectionToDatabaseException) {
            println("User with id $i was skipped due to lack of connection")
            client.connect()
        } catch (e: SQLException) {
            println(e.message)
        }
    }

    // Get communities
    for (i in 1..20) {
        try {
            val community = fetcher.getCommunityById(i)
            if (community == null) {
                println("User with id $i doesn't exist")
                break
            }
            println(community)
            print("Admins: ")
            val admins = fetcher.getCommunityAdmins(i)
            println(admins)
        } catch (e: NoConnectionToDatabaseException) {
            println("Community with id $i was skipped due to lack of connction")
            client.connect()
        } catch (e: SQLException) {
            println(e.message)
        }
    }

    // Get articles
    for (i in 1..20) {
        try {
            val article = fetcher.getArticleById(i)
            if (article == null) {
                println("Article with id $i doesn't exist")
                break
            }
            println(article)
        } catch (e: NoConnectionToDatabaseException) {
            println("Community with id $i was skipped due to lack of connection")
            client.connect()
        } catch (e: SQLException) {
            println(e.message)
        }
    }

    // Get users with id higher, than 10
    println("Users with ID >= 10")
    try {
        fetcher.getUserWithIdGreaterThan(9).forEach(::println)
    } catch (e: NoConnectionToDatabaseException) {
        println("Users were not fetched due to lack of connection")
    } catch (e: SQLException) {
        println(e.message)
    }

    println("Articles with extended information")
    for (i in 1..20) {
        try {
            val article = fetcher.getArticleWithExtendedInformation(i)
            if (article == null) {
                println("Article with id $i doesn't exist")
                break
            }
            println(article)
        } catch (e: NoConnectionToDatabaseException) {
            println("Article with id $i skipped due to lack of connection")
            client.connect()
        } catch (e: SQLException) {
            println(e.message)
        }
    }

    println("Top 10 of most popular names")
    try {
        fetcher.getTop10Names().forEach { (name, count) -> println("$count users has name $name ") }
    } catch (e: NoConnectionToDatabaseException) {
        println("No connection to database")
    } catch (e: SQLException) {
        println(e.message)
    }

    // Delete
    try {
        initializer.deleteTableCommunityAdmins()
        initializer.deleteTableCommunities()
        initializer.deleteTableArticles()
        initializer.deleteTableUsers()
    } catch (e: NoConnectionToDatabaseException) {
        println("No connection to database")
    } catch (e: SQLException) {
        println(e.message)
    }

    client.disconnect()
}