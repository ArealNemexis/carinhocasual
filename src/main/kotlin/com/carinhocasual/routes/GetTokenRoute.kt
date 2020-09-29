package com.carinhocasual.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.auth.*
import io.ktor.gson.*

import com.carinhocasual.resource.basicAuthentication

fun Application.authRoute () {
    routing { 
        basicAuthentication ()

        authenticate ("getAuthToken") {
            get ("/login") {
                val userPrincipal = call.authentication.principal <UserIdPrincipal> ()
                
                //apenas um teste sem usar o Content Negotiation
                call.response.status (HttpStatusCode.OK)
                call.respondText ("Authenticated. UserID: ${userPrincipal?.name}")
            }
        }
    }
}