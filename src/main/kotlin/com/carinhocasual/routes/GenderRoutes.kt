package com.carinhocasual.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

import com.carinhocasual.service.GenderService
import com.carinhocasual.entity.gender.Gender

val genderService = GenderService ()

fun Application.genderRoutes () {
    routing {
        get ("/genders") {
            val genders = genderService.getAll ()
            call.respond (genders)
        }
        
        get ("/gender/{id}") {
            val found = genderService.getOne (call.parameters ["id"].toString ())

            if (found != null) {
                call.response.status (HttpStatusCode.Found)
                call.respond (found)
            } else {
                call.response.status (HttpStatusCode.NotFound)
            }
        }

        post ("/gender") {
            val newGender: Gender = call.receive <Gender> ()
            val validation: Int = genderService.validate (newGender)

            if (validation == 409) {
                call.response.status (HttpStatusCode.Conflict)
            } else if (validation == 401) {
                call.response.status (HttpStatusCode.BadRequest)
            } else {
                genderService.persist(newGender)
                call.response.status (HttpStatusCode.Created)
                call.respond (newGender)
            }
        }

        delete ("/gender/{id}") {
            val parameter: String = call.parameters ["id"].toString ()
            val removed = genderService.remove (parameter)

            if (removed) {
                call.response.status (HttpStatusCode.OK)
            } else {
                call.response.status (HttpStatusCode.NotFound)
            }
        }

        put ("/gender/{id}") {
            val param = call.parameters ["id"].toString ()
            val gender = call.receive <Gender> ()

            val replaced = genderService.replace (param, gender)

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