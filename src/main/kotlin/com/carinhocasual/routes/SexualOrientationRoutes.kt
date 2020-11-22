package com.carinhocasual.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

import com.carinhocasual.service.SexualOrientationService
import com.carinhocasual.entity.sexualOrientation.SexualOrientation
import com.carinhocasual.resource.Response
import com.carinhocasual.resource.exceptions.*

val osService = SexualOrientationService ()

fun Application.sexualOrientationRoutes () {
    routing {
        get ("/sexualorientations") {
            val response = osService.getAll ()
            call.respond (response.getStatusCode (), response)
        }
        
        get ("/sexualorientation/{id}") {
            try {
                val id = call.parameters ["id"].toString ()
                val response = osService.getOne (id)
                call.respond (response.getStatusCode (), response)
            } catch (e: NotFoundException) {
                call.respond (HttpStatusCode.NotFound)
            }
        }

        post ("/sexualorientation") {
            try {
                val newSexualOrientation: SexualOrientation = call.receive <SexualOrientation> ()
                val response = osService.persist(newSexualOrientation)
                call.respond (response.getStatusCode (), response)
            } catch (e: Exception) {
                call.respond (HttpStatusCode.BadRequest)
            }
        }

        delete ("/sexualorientation/{id}") {
            try {
                val id: String = call.parameters ["id"].toString ()
                val response = osService.remove (id)
                call.respond (response.getStatusCode (), response)
            } catch (e: NotFoundException) {
                call.respond (HttpStatusCode.NotFound)
            }
        }

        put ("/sexualorientation/{id}") {
            try {
                val id = call.parameters ["id"].toString ()
                val sexualOrientation = call.receive <SexualOrientation> ()
                val response = osService.replace(id, sexualOrientation)
                call.respond (response.getStatusCode (), response)
            } catch (e:Exception) {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}
