package ru.kinjalik

data class Config(
    val database: DatabaseConfig,
    val deployment: DeploymentConfig
)

data class DatabaseConfig(
    val url: String,
    val user: String,
    val password: String
)

data class DeploymentConfig(
    val port: Int
)