package ru.kinjalik

import com.typesafe.config.ConfigFactory
import io.github.config4k.extract
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.ktor.di
import org.kodein.di.singleton
import ru.kinjalik.faculty.facultyComponents
import ru.kinjalik.faculty.facultyModule
import ru.kinjalik.plugins.*
import ru.kinjalik.student.studentComponents
import ru.kinjalik.student.studentModule

fun main(args: Array<String>): Unit  {
    val config = ConfigFactory.load().extract<Config>()

    migrate(config.database)

    val engine = embeddedServer(Netty, port = config.deployment.port) {
        di {
            coreComponents(config)
            facultyComponents()
            studentComponents()
        }
        configureSerialization()

        facultyModule()
        studentModule()
    }
    engine.start(wait = true)
}

fun DI.Builder.coreComponents(config: Config) {
    bind<Config>() with singleton { config }

    bind<Database>() with singleton {
        Database.connect(
            url = config.database.url,
            user = config.database.user,
            password = config.database.password
        )
    }
}

fun migrate(db: DatabaseConfig) {
    Flyway
        .configure()
        .dataSource(db.url, db.user, db.password)
        .load()
        .migrate()
}