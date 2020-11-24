package com.carinhocasual.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

import com.carinhocasual.service.GenderService
import com.carinhocasual.entity.gender.Gender
import com.carinhocasual.resource.Response
import com.carinhocasual.resource.exceptions.*
import java.lang.*

val genderService = GenderService ()

fun Application.genderRoutes () {
    routing {
        get ("/genders") {
            val response = genderService.getAll ()
            call.respond (response.getStatusCode (), response)
        }

        get ("/gender/{id}") {
            try {
                val id = call.parameters ["id"].toString ()
                val response = genderService.getOne (id)
                call.respond (response.getStatusCode (), response)
            } catch (e: NotFoundException) {
                call.respond (HttpStatusCode.NotFound)
            }
        }

        post ("/gender") {
            try {
                val newGender: Gender = call.receive <Gender> ()
                val response = genderService.persist (newGender)
                call.respond (response.getStatusCode (), response)
            } catch (e: Exception) {
                call.respond (HttpStatusCode.BadRequest)
            }
        }

        delete ("/gender/{id}") {
            try {
                val id: String = call.parameters ["id"].toString ()
                val response = genderService.remove (id)
                call.respond (response.getStatusCode (), response)
            } catch (e: NotFoundException) {
                call.respond (HttpStatusCode.NotFound)
            }
        }

        put ("/gender/{id}") {
            try {
                val id = call.parameters ["id"].toString ()
                val gender = call.receive <Gender> ()
                val response = genderService.replace(id, gender)
                call.respond (response.getStatusCode (), response)
            } catch (e: Exception) {
                call.respond (HttpStatusCode.BadRequest)
            }
        }
    }
}
