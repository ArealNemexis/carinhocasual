package com.carinhocasual.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

import com.carinhocasual.service.InterestService
import com.carinhocasual.entity.interest.Interest

val interestService = InterestService ()

fun Application.interestRoutes () {
    routing {
        get ("/interests") {
            val interests = interestService.getAll ()
            call.response.status (HttpStatusCode.OK)
            call.respond (interests)
        }
        
        get ("/interest/{id}") {
            val found = interestService.getOne (call.parameters ["id"].toString ())

            if (found != null) {
                call.response.status (HttpStatusCode.Found)
                call.respond (found)
            } else {
                call.response.status (HttpStatusCode.NotFound)
            }
        }

        post ("/interest") {
            val newInterest: Interest = call.receive <Interest> ()
            val validation: Int = interestService.validate (newInterest)

            if (validation == 409) {
                call.response.status (HttpStatusCode.Conflict)
            } else if (validation == 401) {
                call.response.status (HttpStatusCode.BadRequest)
            } else {
                interestService.persist(newInterest)
                call.response.status (HttpStatusCode.Created)
                call.respond (newInterest)
            }
        }

        delete ("/interest/{id}") {
            val parameter: String = call.parameters ["id"].toString ()
            val removed = interestService.remove (parameter)

            if (removed) {
                call.response.status (HttpStatusCode.OK)
            } else {
                call.response.status (HttpStatusCode.NotFound)
            }
        }

        put ("/interest/{id}") {
            val param = call.parameters ["id"].toString ()
            val newInterest = call.receive <Interest> ()

            val replaced = interestService.replace (param, newInterest)

            if (replaced == 200) {
                call.response.status (HttpStatusCode.OK)
                call.respond (newInterest)
            } else if (replaced == 409) {
                call.response.status (HttpStatusCode.Conflict)
            } else if (replaced == 401) {
                call.response.status (HttpStatusCode.BadRequest)
            }
        }
    }
}