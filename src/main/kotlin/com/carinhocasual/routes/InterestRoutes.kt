package com.carinhocasual.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

import com.carinhocasual.service.InterestService
import com.carinhocasual.entity.interest.Interest
import com.carinhocasual.resource.Response
import com.carinhocasual.resource.exceptions.*

val interestService = InterestService ()

fun Application.interestRoutes () {
    routing {
        get ("/interests") {
            val response = interestService.getAll ()
            call.respond (response.getStatusCode (), response)
        }
        
        get ("/interest/{id}") {
            try {
                val id = call.parameters ["id"].toString ()
                val response = interestService.getOne (id)
                call.respond (response.getStatusCode (), response)
            } catch (e: NotFoundException) {
                call.respond (HttpStatusCode.NotFound)
            }
        }

        post ("/interest") {
            try {
                val newInterest: Interest = call.receive <Interest> ()
                val response = interestService.persist(newInterest)
                call.respond (response.getStatusCode (), response)
            } catch (e: Exception) {
                call.respond (HttpStatusCode.BadRequest)
            }
        }

        delete ("/interest/{id}") {
            try {
                val id: String = call.parameters ["id"].toString ()
                val response = interestService.remove (id)
                call.respond (response.getStatusCode (), response)
            } catch (e: NotFoundException) {
                call.respond (HttpStatusCode.NotFound)
            }
        }

        put ("/interest/{id}") {
            try {
                val id = call.parameters ["id"].toString ()
                val interest = call.receive <Interest> ()
                val response = interestService.replace (id, interest)
                call.respond (response.getStatusCode (), response)
            } catch (e: Exception) {
                call.respond (HttpStatusCode.BadRequest)
            }
        }
    }
}
