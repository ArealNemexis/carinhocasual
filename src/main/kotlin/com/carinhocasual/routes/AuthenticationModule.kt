package com.carinhocasual.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.auth.*
import io.ktor.gson.*

<<<<<<< HEAD
import com.carinhocasual.resource.basicAuthentication
// import com.carinhocasual.resource.jwtAuthorization
=======
import com.carinhocasual.resource.JwtHandler
>>>>>>> 98bfcd1fb0d22d43f02f2d0b113d27c3576bb215
import com.carinhocasual.resource.Response

data class jwtResponse (val token_type: String, val access_token: String, val expires_in: Int, val expires_on: Long)

val jwth = JwtHandler ()

fun Application.authRoute () {
    routing { 
        authenticate ("basic") {
            get ("/login") {
                val userPrincipal = call.authentication.principal <UserIdPrincipal> ()
                call.response.header ("X-JWT", "${jwth.makeToken(userPrincipal!!.name)}")
                call.respond (Response (userPrincipal, 200))
            }
        }
    }
}
