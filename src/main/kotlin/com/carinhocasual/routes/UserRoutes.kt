package com.carinhocasual.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

import com.carinhocasual.service.PersonService
import com.carinhocasual.entity.person.User
import com.carinhocasual.resource.Response
import com.carinhocasual.resource.exceptions.*
import java.lang.*

val personService = PersonService ()

fun Application.userRoutes () {
    routing {
        get ("/users") {
            val users = personService.getAll ()
            call.respond (users.getStatusCode (), users)
        }

        get ("/user/{id}") {
            try {
                val id = call.parameters ["id"].toString ()
                val response = personService.getOne (id)
                call.respond (response.getStatusCode (), response)
            } catch (e: NotFoundException) {
                call.respond (HttpStatusCode.NotFound)
            }
        }

        post ("/user") {
            try {
                val newUser: User = call.receive <User> ()
                val response = personService.persist(newUser)
                call.respond (response.getStatusCode (), response)
            } catch (e: Exception) {
                call.respond (HttpStatusCode.BadRequest)
            }
        }

        delete ("/user/{id}") {
            try {
                val id: String = call.parameters ["id"].toString ()
                val response = personService.remove (id)
                call.respond (response.getStatusCode (), response)
            } catch (e: NotFoundException) {
                call.respond (HttpStatusCode.NotFound)
            }
        }

        put ("/user/{id}") {
            try {
                val id = call.parameters ["id"].toString ()
                val user = call.receive <User> ()
                val response = personService.replace (id, user)
                call.respond (response.getStatusCode (), response)
            } catch (e: Exception) {
                call.respond (HttpStatusCode.BadRequest)
            }
        }
    }
}
