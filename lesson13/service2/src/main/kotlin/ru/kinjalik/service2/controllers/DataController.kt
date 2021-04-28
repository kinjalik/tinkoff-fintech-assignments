package ru.kinjalik.service2.controllers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClientResponseException
import org.springframework.web.server.ResponseStatusException
import ru.kinjalik.service2.integrations.BasicUserDataIntegration
import ru.kinjalik.service2.models.BasicUserData
import ru.kinjalik.service2.models.Quote
import ru.kinjalik.service2.models.User
import ru.kinjalik.service2.properties.ExternalProperties
import javax.sql.DataSource


@RestController
@RequestMapping("/")
class DataController(
    private val dataSource: DataSource,
    private val properties: ExternalProperties
) {

    @GetMapping("/{id}")
    fun getUserQuotations(@PathVariable id: Int): User? {
        try {
            val author = BasicUserDataIntegration(properties).getById(id)

            val quotes = mutableListOf<Quote>()

            dataSource.connection.use {
                val statement = it.prepareStatement("SELECT id, text FROM quotations WHERE author_id = ?")
                statement.setInt(1, id)
                statement.execute()
                val queryRes = statement.resultSet

                while (queryRes.next()) {
                    quotes.add(
                        Quote(
                            queryRes.getInt("id"),
                            queryRes.getString("text")
                        )
                    )
                }

                queryRes.close()
                statement.close()
                return User(author.id, author.name, author.email, quotes.toList())
            }
        } catch (e: WebClientResponseException) {
            if (e.rawStatusCode == 404)
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find user with id $id")
            else
                throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Troubles with first service")
        }
    }
}