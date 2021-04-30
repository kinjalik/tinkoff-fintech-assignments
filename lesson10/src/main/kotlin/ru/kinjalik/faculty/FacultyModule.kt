package ru.kinjalik.faculty

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.request.*
import jdk.net.SocketFlow
import kotlinx.serialization.Serializable
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import org.kodein.di.singleton
import ru.kinjalik.student.StudentDao

fun Application.facultyModule() {
    val dao: FacultyDao by closestDI().instance()
    val studentDao: StudentDao by closestDI().instance()

    routing {
        route("/faculties") {
            get {
                call.respond(dao.getAll())
            }

            get("{id}") {
                val id = call.parameters["id"]?.toInt() ?: throw NoSuchElementException("This faculty not found")
                try {
                    val res = dao.getById(id)
                    call.respond(res)
                } catch (e: NoSuchElementException) {
                    call.respond(HttpStatusCode.NotFound, e.message.toString())
                }
            }

            get("{id}/students") {
                try {
                    val id = call.parameters["id"]?.toInt() ?: throw NoSuchElementException("Incorrect faculty id provided")
                    val res = studentDao.getByFacultyId(id)
                    call.respond(res)
                } catch (e: NoSuchElementException) {
                    call.respond(HttpStatusCode.NotFound, e.message.toString())
                }
            }

            post {
                val request = call.receive<CreateFacultyRequest>()
                call.respond(dao.add(request.name, request.foundedDate))
            }
        }
    }
}

@Serializable
private data class CreateFacultyRequest(
    val name: String,
    val foundedDate: String
    )

fun DI.Builder.facultyComponents() {
    bind<FacultyDao>() with singleton { FacultyDao(instance()) }
}