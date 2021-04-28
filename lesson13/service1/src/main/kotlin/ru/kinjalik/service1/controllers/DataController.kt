package ru.kinjalik.service1.controllers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import ru.kinjalik.service1.models.User
import javax.sql.DataSource


@RestController
@RequestMapping("/")
class DataController(
    private val dataSource: DataSource
) {

    @GetMapping("/{id}")
    fun getUserData(@PathVariable id: Int): User? {
        dataSource.connection.use {
            val statement = it.prepareStatement("SELECT id, name, email FROM users WHERE id = ?")
            statement.setInt(1, id)
            statement.execute()
            val queryRes = statement.resultSet
            if (!queryRes.next())
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find user with id $id")

            val res = User (
                queryRes.getInt("id"),
                queryRes.getString("name"),
                queryRes.getString("email")
                    )
            statement.close()
            return res
        }
    }
}