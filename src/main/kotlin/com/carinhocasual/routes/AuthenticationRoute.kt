package com.carinhocasual.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.auth.*
import io.ktor.gson.*

import com.carinhocasual.resource.basicAuthentication
import com.carinhocasual.resource.jwtAuthorization
import com.carinhocasual.resource.Response

fun Application.authRoute () {
    
    routing { 
        basicAuthentication ()

        authenticate ("getAuthToken") {
            get ("/login") {
                val userPrincipal = call.authentication.principal <UserIdPrincipal> ()
                call.respond (Response (userPrincipal!!, 200))
                // call.respond (HttpStatusCode.OK, Response ("${userPrincipal?.name}", 200))
            }
        }
    }
}
