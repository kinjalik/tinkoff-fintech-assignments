package database

import entities.Article
import entities.Community
import entities.CommunityAdmin
import entities.User

class DatabaseInitializer(
    val client: DatabaseClient
) {
    fun createTableUsers() {
        val sql = "CREATE TABLE IF NOT EXISTS users\n" +
                "(\n" +
                "    id INTEGER\n" +
                "        CONSTRAINT users_pk\n" +
                "            PRIMARY KEY autoincrement,\n" +
                "    name TEXT NOT NULL,\n" +
                "    surname TEXT NOT NULL,\n" +
                "    regDate DATETIME DEFAULT CURRENT_TIMESTAMP\n" +
                ");"

        executeQueryWithoutArguments(sql)
        val demoData = listOf(
            User(1, "Albert", "Akmukhametov", "2021-03-17 14:48:31"),
            User(2, "Ivan", "Ivanov", "2021-03-17 14:48:31"),
            User(3, "Mikhail", "Mikhailov", "2021-03-19 08:11:31"),
            User(4, "Albert", "NeAkmukhametov", "2021-03-19 09:13:14"),
            User(5, "Arthur", "alwkfa", "2021-03-19 09:17:03"),
            User(6, "Arthur", "Fawkfljaw", "2021-03-19 09:17:03"),
            User(7, "Ivan", "flakwjf", "2021-03-19 09:17:03"),
            User(8, "John", "aawf", "2021-03-19 09:17:03"),
            User(9, "LKAjfaw", "afw", "2021-03-19 09:17:03"),
            User(10, "AFawkl", "aklaw", "2021-03-19 09:17:03"),
            User(11, "Anna", "K", "2021-03-19 09:17:03"),
            User(12, "Lev", "K", "2021-03-19 09:17:35"),
            User(13, "Zein", "K", "2021-03-19 09:17:35"),
            User(14, "Sanzhar", "Zh", "2021-03-19 09:17:49"),
            User(15, "Ivan", "K", "2021-03-19 09:18:49")
        )
        val insertQuery = "INSERT INTO users (name, surname, regDate) VALUES (?, ?, ?)";
        for (user in demoData) {
            val statement = client.getPreparedStatement(insertQuery);
            statement.setString(1, user.name)
            statement.setString(2, user.surname)
            statement.setString(3, user.regDate)
            statement.execute()
            statement.close()
        }
    }

    fun deleteTableUsers() = deleteTable("users")

    fun createTableCommunities() {
        val sql = "CREATE TABLE IF NOT EXISTS communities\n" +
                "(\n" +
                "    id INTEGER\n" +
                "        CONSTRAINT communities_pk\n" +
                "            PRIMARY KEY autoincrement,\n" +
                "    name TEXT NOT NULL,\n" +
                "    membersCount INTEGER NOT NULL DEFAULT 1,\n" +
                "    isClose INTEGER NOT NULL\n" +
                ");"
        executeQueryWithoutArguments(sql)

        val insertQuery = "INSERT INTO communities (name, membersCount, isClose) VALUES (?, ?, ?)"

        val demoData = listOf(
            Community(1, "BS20-01",30, true),
            Community(2, "University News", 1337, false),
            Community(3, "City News", 2392, false)
        )

        for (community in demoData) {
            val statement = client.getPreparedStatement(insertQuery)
            statement.setString(1, community.name)
            statement.setInt(2, community.membersCount)
            statement.setBoolean(3, community.isClosed)

            statement.execute()
            statement.close()
        }
    }

    fun deleteTableCommunities() = deleteTable("communities")

    fun createTableArticles() {
        val sql = "CREATE TABLE IF NOT EXISTS articles\n" +
                "(\n" +
                "    id INTEGER\n" +
                "        CONSTRAINT articles_pk\n" +
                "            PRIMARY KEY autoincrement,\n" +
                "    name TEXT NOT NULL,\n" +
                "    text TEXT NOT NULL,\n" +
                "    authorId INTEGER NOUT NULL\n" +
                "        CONSTRAINT articles_author\n" +
                "            REFERENCES users\n" +
                "            ON DELETE CASCADE,\n" +
                "    publicationTime INTEGER DEFAULT CURRENT_TIMESTAMP\n" +
                ");"
        executeQueryWithoutArguments(sql)

        val demoData = listOf(
            Article(1, "History of Russia", "Some heroic text blah-blah-blah", "2021-03-19 09:03:41", 1),
            Article(2, "Compilers", "Compiler descriptio blah-blah-lbah",  "2021-03-19 09:03:41", 1)
        )
        val insertQuery = "INSERT INTO articles (name, text, authorId, publicationTime) VALUES (?, ?, ?, ?)";
        for (article in demoData) {
            val statement = client.getPreparedStatement(insertQuery)
            statement.setString(1, article.name)
            statement.setString(2, article.text)
            statement.setInt(3, article.authorId)
            statement.setString(4, article.publicationTime)
            statement.execute()
            statement.close()
        }
    }

    fun deleteTableArticles() = deleteTable("articles")

    fun createTableCommunityAdmins() {
        val sql = "CREATE TABLE IF NOT EXISTS community_admins\n" +
                "(\n" +
                "    user_id INTEGER\n" +
                "        CONSTRAINT community_admins_users\n" +
                "            REFERENCES users\n" +
                "            ON DELETE CASCADE,\n" +
                "    community_id INTEGER\n" +
                "        CONSTRAINT community_admins_community\n" +
                "            REFERENCES communities\n" +
                "            ON DELETE CASCADE\n" +
                ");"
        executeQueryWithoutArguments(sql)

        val insertQuery = "INSERT INTO community_admins (user_id, community_id) VALUES (?, ?)";
        val demoData = listOf(
            Pair(1, 1),
            Pair(2, 2),
            Pair(3, 3),
            Pair(4, 1),
            Pair(5, 2),
            Pair(6, 3)
        )

        for (pair in demoData) {
            val statement = client.getPreparedStatement(insertQuery)
            statement.setInt(1, pair.first)
            statement.setInt(2, pair.second)

            statement.execute()
            statement.close()
        }
    }

    fun deleteTableCommunityAdmins() = deleteTable("community_admins")

    private fun executeQueryWithoutArguments(sql: String) {
        if (!client.connected) {
            client.connect()
        }

        val statement = client.getPreparedStatement(sql)
        statement.execute()
        statement.close()
    }

    private fun deleteTable(name: String) {
        val sql = "DROP TABLE IF EXISTS $name"
        if (!client.connected) {
            client.connect()
        }

        val statement = client.getPreparedStatement(sql)
        statement.execute()
        statement.close()
    }
}