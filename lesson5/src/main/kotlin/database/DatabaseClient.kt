package database

import exceptions.NoConnectionToDatabaseException
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.SQLException

class DatabaseClient(
    private val sqliteUrl: String
) {
    private var connection: Connection? = null

    fun connect() {
        connection = DriverManager.getConnection(sqliteUrl)
    }

    fun getPreparedStatement(query: String): PreparedStatement {
        if (connected)
            return connection!!.prepareStatement(query)
        else
            throw NoConnectionToDatabaseException()
    }

    val connected: Boolean
        get() = !(connection?.isClosed ?: true)

    fun disconnect() {
        connection?.close()
    }
}