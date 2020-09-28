package com.carinhocasual.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

import com.carinhocasual.service.PersonService
import com.carinhocasual.entity.person.Person
import com.carinhocasual.entity.person.User
import com.carinhocasual.entity.person.Admin

val personService = PersonService ()

fun Application.userRoutes () {
    routing {
        get ("/users") {
            val users = personService.getAll ()
            call.respond (users)
        }
        
        get ("/user/{id}") {
            val found = personService.getOne (call.parameters ["id"].toString ())

            if (found != null) {
                call.response.status (HttpStatusCode.Found)
                call.respond (found)
            } else {
                call.response.status (HttpStatusCode.NotFound)
            }
        }

        post ("/user") {
            val newUser: User = call.receive <User> ()
            val validation: Int = personService.validate (newUser)

            if (validation == 409) {
                call.response.status (HttpStatusCode.Conflict)
            } else if (validation == 401) {
                call.response.status (HttpStatusCode.BadRequest)
            } else {
                personService.persist(newUser)
                call.response.status (HttpStatusCode.Created)
                call.respond (newUser)
            }
        }

        delete ("/user/{id}") {
            val parameter: String = call.parameters ["id"].toString ()
            val removed = personService.remove (parameter)

            if (removed) {
                call.response.status (HttpStatusCode.OK)
            } else {
                call.response.status (HttpStatusCode.NotFound)
            }
        }

        put ("/user/{id}") {
            val param = call.parameters ["id"].toString ()
            val gender = call.receive <User> ()

            val replaced = personService.replace (param, gender)

            if (replaced == 200) {
                call.response.status (HttpStatusCode.OK)
                call.respond (gender)
            } else if (replaced == 409) {
                call.response.status (HttpStatusCode.Conflict)
            } else if (replaced == 401) {
                call.response.status (HttpStatusCode.BadRequest)
            }
        }
    }
}