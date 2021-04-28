package ru.kinjalik.service2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import ru.kinjalik.service2.properties.ExternalProperties

@SpringBootApplication
@EnableConfigurationProperties(ExternalProperties::class)
class Service2Application

fun main(args: Array<String>) {
    runApplication<Service2Application>(*args)
}
