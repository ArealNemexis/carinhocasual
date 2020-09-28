package com.carinhocasual.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

import com.carinhocasual.service.SexualOrientationService
import com.carinhocasual.entity.sexualOrientation.SexualOrientation

val osService = SexualOrientationService ()

fun Application.sexualOrientationRoutes () {
    routing {
        get ("/sexualorientations") {
            val sexualOrientations = osService.getAll ()
            call.response.status (HttpStatusCode.OK)
            call.respond (sexualOrientations)
        }
        
        get ("/sexualorientation/{id}") {
            val found = osService.getOne (call.parameters ["id"].toString ())

            if (found != null) {
                call.response.status (HttpStatusCode.Found)
                call.respond (found)
            } else {
                call.response.status (HttpStatusCode.NotFound)
            }
        }

        post ("/sexualorientation") {
            val newSexualOrientation: SexualOrientation = call.receive <SexualOrientation> ()
            val validation: Int = osService.validate (newSexualOrientation)

            if (validation == 409) {
                call.response.status (HttpStatusCode.Conflict)
            } else if (validation == 401) {
                call.response.status (HttpStatusCode.BadRequest)
            } else {
                osService.persist(newSexualOrientation)
                call.response.status (HttpStatusCode.Created)
                call.respond (newSexualOrientation)
            }
        }

        delete ("/sexualorientation/{id}") {
            val parameter: String = call.parameters ["id"].toString ()
            val removed = osService.remove (parameter)

            if (removed) {
                call.response.status (HttpStatusCode.OK)
            } else {
                call.response.status (HttpStatusCode.NotFound)
            }
        }

        put ("/sexualorientation/{id}") {
            val param = call.parameters ["id"].toString ()
            val newSexualOrientation = call.receive <SexualOrientation> ()

            val replaced = osService.replace (param, newSexualOrientation)

            if (replaced == 200) {
                call.response.status (HttpStatusCode.OK)
                call.respond (newSexualOrientation)
            } else if (replaced == 409) {
                call.response.status (HttpStatusCode.Conflict)
            } else if (replaced == 401) {
                call.response.status (HttpStatusCode.BadRequest)
            }
        }
    }
}