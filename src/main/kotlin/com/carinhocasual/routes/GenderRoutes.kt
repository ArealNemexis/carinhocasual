package com.carinhocasual.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

import com.carinhocasual.service.GenderService
import com.carinhocasual.entity.gender.Gender
import com.carinhocasual.resource.Response

val genderService = GenderService ()

fun Application.genderRoutes () {
    routing {
        get ("/genders") {
            val genders = genderService.getAll ()
            call.respond (Response (genders))
        }
        
        get ("/gender/{id}") {
            val found = genderService.getOne (call.parameters ["id"].toString ())

            if (found != null) {
                call.respond (HttpStatusCode.Found, Response (found))
            } else {
                call.respond (HttpStatusCode.NotFound, Response ("Object not found"))
            }
        }

        post ("/gender") {
            val newGender: Gender = call.receive <Gender> ()
            val validation: Int = genderService.validate (newGender)

            if (validation == 409) {
                call.respond (HttpStatusCode.Conflict, Response ("This label already exists"))
            } else if (validation == 401) {
                call.respond (HttpStatusCode.BadRequest, Response ("Bad request"))
            } else {
                genderService.persist(newGender)
                call.respond (HttpStatusCode.Created, Response (newGender))
            }
        }

        delete ("/gender/{id}") {
            val parameter: String = call.parameters ["id"].toString ()
            val removed = genderService.remove (parameter)

            if (removed) {
                call.respond (HttpStatusCode.OK, Response ("Object deleted"))
            } else {
                call.respond (HttpStatusCode.NotFound, Response ("Object not found"))
            }
        }

        put ("/gender/{id}") {
            val param = call.parameters ["id"].toString ()
            val gender = call.receive <Gender> ()

            val replaced = genderService.replace (param, gender)

            if (replaced == 200) {
                call.respond (HttpStatusCode.OK, Response (gender))
            } else if (replaced == 409) {
                call.respond (HttpStatusCode.Conflict, Response ("This label already exists"))
            } else if (replaced == 401) {
                call.respond (HttpStatusCode.BadRequest, Response ("Bad Request"))
            }
        }
    }
}
