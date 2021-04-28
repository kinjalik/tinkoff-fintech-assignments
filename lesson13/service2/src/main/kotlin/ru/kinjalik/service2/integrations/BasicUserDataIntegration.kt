package ru.kinjalik.service2.integrations

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import ru.kinjalik.service2.models.BasicUserData
import ru.kinjalik.service2.properties.ExternalProperties

@Component
class BasicUserDataIntegration(
    properties: ExternalProperties
) {

    private val addr = "http://${properties.address}:${properties.port}"
    private val vebClient = WebClient.create(addr)

    fun getById(id: Int): BasicUserData {
        return vebClient
            .get()
            .uri("/$id")
            .retrieve()
            .bodyToMono(BasicUserData::class.java)
            .block()!!
    }
}
