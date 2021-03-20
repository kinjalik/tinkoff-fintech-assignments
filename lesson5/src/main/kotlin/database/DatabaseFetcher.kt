package database

import entities.Article
import entities.ArticleExtended
import entities.Community
import entities.User

class DatabaseFetcher (
    val client: DatabaseClient
){
    // 6.a
    fun getUserById(id: Int): User? {
        val statement = client.getPreparedStatement("SELECT * FROM users WHERE id=?")
        statement.setInt(1, id)
        statement.execute()

        val queryRes = statement.resultSet.also { it.next() }
        if (queryRes.isClosed) {
            return null;
        }
        val res = User(
            id,
            queryRes.getString("name"),
            queryRes.getString("surname"),
            queryRes.getString("regDate")
        )
        statement.close()
        return res
    }
    fun getCommunityById(id: Int): Community? {
        val statement = client.getPreparedStatement("SELECT * FROM communities WHERE id=?")
        statement.setInt(1, id)
        statement.execute()

        val queryRes = statement.resultSet.also { it.next() }
        if (queryRes.isClosed) {
            return null;
        }
        val res = Community(
            id,
            queryRes.getString("name"),
            queryRes.getInt("membersCount"),
            queryRes.getBoolean("isClose")
        )
        statement.close()
        return res
    }
    fun getArticleById(id: Int): Article? {
        val statement = client.getPreparedStatement("SELECT * FROM articles WHERE id=?")
        statement.setInt(1, id)
        statement.execute()

        val queryRes = statement.resultSet.also { it.next() }
        if (queryRes.isClosed) {
            return null;
        }
        val res = Article(
            id,
            queryRes.getString("name"),
            queryRes.getString("text"),
            queryRes.getString("publicationTime"),
            queryRes.getInt("authorId")
        )
        statement.close()
        return res
    }

    fun getCommunityAdmins(id: Int): List<User> {
        val statement = client.getPreparedStatement("SELECT * FROM community_admins WHERE community_id=?")
        statement.setInt(1, id)
        statement.execute()

        val queryRes = statement.resultSet
        val res = mutableListOf<User>()
        while (queryRes.next()) {
            val uid = queryRes.getInt("user_id")
            val user: User? = getUserById(uid)
            if (user != null) {
                res.add(user)
            } else {
                System.err.println("Not found user with ID $uid")
            }
        }
        statement.close()
        return res.toList()
    }

    // 6.b
    fun getUserWithIdGreaterThan(id: Int): List<User> {
        val statement = client.getPreparedStatement("SELECT * FROM users WHERE id>?")
        statement.setInt(1, id)
        statement.execute()
        val queryRes = statement.resultSet
        val users = mutableListOf<User>()

        while (queryRes.next()) {
            users.add(User(
                queryRes.getInt("id"),
                queryRes.getString("name"),
                queryRes.getString("surname"),
                queryRes.getString("regDate")
            ))
        }
        statement.close()
        return users.toList()
    }

    // 6.c
    fun getArticleWithExtendedInformation(id: Int): ArticleExtended? {
        if (!client.connected) {
            client.connect()
        }

        val statement = client.getPreparedStatement("SELECT articles.id   as id,\n" +
                "       articles.name as name,\n" +
                "       text,\n" +
                "       publicationTime,\n" +
                "       authorId,\n" +
                "       u.name        as authorName,\n" +
                "       u.surname     as authorSurname\n" +
                "FROM articles\n" +
                "         LEFT JOIN users u on u.id = articles.authorId\n" +
                "WHERE articles.id = ?")
        statement.setInt(1, id)
        statement.execute()
        val queryRes = statement.resultSet.also { it.next() }
        if (queryRes.isClosed) {
            return null;
        }
        val res = ArticleExtended(
            queryRes.getInt("id"),
            queryRes.getString("name"),
            queryRes.getString("text"),
            queryRes.getString("publicationTime"),
            queryRes.getInt("authorId"),
            queryRes.getString("authorName"),
            queryRes.getString("authorSurname")
        )
        statement.close()
        return res
    }

    // 6.d and 6.e
    fun getTop10Names(): Map<String, Int> {
        if (!client.connected)
            client.connect()
//
        val statement = client.getPreparedStatement("SELECT name, COUNT(name) as count\n" +
                "FROM users\n" +
                "GROUP BY name\n" +
                "ORDER BY count DESC\n" +
                "LIMIT 10")
        statement.execute()
        val queryRes = statement.resultSet
        val res = mutableMapOf<String, Int>()
        while (queryRes.next()) {
            val key = queryRes.getString("name")
            val value = queryRes.getInt("count")
            res[key] = value
        }
        statement.close()
        return res.toMap()
    }
}