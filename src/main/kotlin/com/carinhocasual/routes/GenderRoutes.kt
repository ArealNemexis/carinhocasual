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
            val response = genderService.getAll ()
            call.respond (HttpStatusCode (response.getStatusCode (), ""), response)
        }
        
        get ("/gender/{id}") {
            val id = call.parameters ["id"].toString ()
            val response = genderService.getOne (id)
            call.respond (response)
        }

        post ("/gender") {
            val newGender: Gender = call.receive <Gender> ()
            val response = genderService.persist (newGender)
            call.respond(response)
            
            // try {
            //     response = genderService.persist(newGender)
            // } catch {

            // }
            
        }

        delete ("/gender/{id}") {
            val id: String = call.parameters ["id"].toString ()
            val response = genderService.remove (id)
            call.respond (response)
        }

        // put ("/gender/{id}") {
        //     val id = call.parameters ["id"].toString ()
        //     val gender = call.receive <Gender> ()
            
        // }
    }
}
