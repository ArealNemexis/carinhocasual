package com.carinhocasual.resource

import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import com.auth0.jwt.*
import com.auth0.jwt.algorithms.*
import com.carinhocasual.db
import com.carinhocasual.resource.JwtHandler

fun Authentication.authentications () {

    basic (name = "basicAuthentication") {
    realm = "api.carinhocasual.com"
    validate { authForm -> 
        val user = db.users.find { it.getEmail () == authForm.name && it.getPass () == authForm.password }
        if (user != null) UserIdPrincipal (user.getId ()) else null
    }
    

        val issuer: String = "api.carinhocasual.com"
        val algorithm = Algorithm.HMAC256("secret_key")

        fun createJwtVerifier (issuer: String): JWTVerifier = JWT
            .require (algorithm)
            .withIssuer (issuer)
            .build ()

        jwt (name = "jwtAuthorization"){
            realm = "jwt authorization"
            verifier (createJwtVerifier(issuer))
            validate { credential -> if (credential.payload.audience.contains (issuer)) JWTPrincipal (credential.payload) else null }
            
        }
    }
}