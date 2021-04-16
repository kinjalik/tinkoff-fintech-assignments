package com.example.lesson9.integration

import com.example.lesson9.model.Faculty
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class FacultyIntegrationComponent {
    private val webClient = WebClient.create("http://localhost:8081")

    fun getFacultyById(id: Int): Faculty? {
        return webClient
            .get()
            .uri("/faculties/$id")
            .retrieve()
            .bodyToMono(Faculty::class.java)
            .block()
    }
}