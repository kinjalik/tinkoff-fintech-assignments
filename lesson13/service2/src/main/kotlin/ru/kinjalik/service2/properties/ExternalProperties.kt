package ru.kinjalik.service2.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("external")
data class ExternalProperties(
    var address: String,
    var port: String
)