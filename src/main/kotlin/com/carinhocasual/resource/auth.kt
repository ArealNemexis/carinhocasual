package com.carinhocasual.resource

import io.ktor.application.*
import io.ktor.auth.jwt.*
import io.ktor.auth.*
import com.carinhocasual.db
import com.carinhocasual.resource.JwtHandler

val jwth = JwtHandler ()

fun Application.authenticationModule () {
    
    install (Authentication) {
        basic (name = "basic") {
            realm = "Login"
            validate { authForm -> 
                val user = db.users.find { it.getEmail () == authForm.name && it.getPass () == authForm.password }

                if (user != null) {
                    UserIdPrincipal (user.getId ())
                } else {
                    null
                }
            }
        }

        jwt (name = "jwt") {
            realm = "api.carinhocasual.com"
            verifier (jwth.jwtVerifier)
            validate { token -> 
                val userId = token.payload.getClaim("userID").asString ()
                println (userId)
                if (userId != null) {
                    JWTPrincipal (token.payload)
                } else {
                    null
                }
            }
        }
    }
}