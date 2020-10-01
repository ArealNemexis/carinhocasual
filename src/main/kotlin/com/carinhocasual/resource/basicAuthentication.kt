package com.carinhocasual.resource

import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.auth.*
import com.carinhocasual.db

fun Route.basicAuthentication () {
    application.install (Authentication) {
        basic (name = "getAuthToken") {
            realm = "Get a JWT Token"
            validate { authForm -> 
                val user = db.users.find { it.getEmail () == authForm.name && it.getPass () == authForm.password }

                if (user != null) {
                    UserIdPrincipal (user.getId ())
                } else {
                    null
                }
            }
        }
    }
}