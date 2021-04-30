package ru.kinjalik.student

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.Serializable
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import org.kodein.di.singleton

fun Application.studentModule() {
    val dao: StudentDao by closestDI().instance()

    routing {
        route("/students") {
            get {
                call.respond(dao.getAll())
            }

            get("{id}") {
                try {
                    val id = call.parameters["id"]?.toInt() ?: throw NoSuchElementException()
                    val res = dao.getById(id)
                    call.respond(res)
                } catch (e: NoSuchElementException) {
                    call.respond(HttpStatusCode.NotFound, "No such student found");
                }
            }

            post {
                val request = call.receive<CreateStudentRequest>()
                call.respond(dao.add(request.name, request.surname))
            }

            get("{id}/moveToGroup/{fid}") {
                try {
                    val id = call.parameters["id"]?.toInt() ?: throw NoSuchElementException("Incorrect student id provided")
                    val fid = call.parameters["fid"]?.toInt() ?: throw NoSuchElementException("Incorrect faculty id provided")
                    dao.setGroup(id, fid)
                    call.respond("OK")
                } catch (e: NoSuchElementException) {
                    call.respond(e.message.toString())
                }
            }
        }
    }
}

@Serializable
private data class CreateStudentRequest(
    val name: String,
    val surname: String
)

fun DI.Builder.studentComponents() {
    bind<StudentDao>() with singleton { StudentDao(instance()) }
}
