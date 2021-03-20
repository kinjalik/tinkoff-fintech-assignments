package database

class DatabaseInitializer (
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