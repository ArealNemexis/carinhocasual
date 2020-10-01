package com.carinhocasual.resource

import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import com.auth0.jwt.*
import com.auth0.jwt.algorithms.*
import java.util.*
import com.carinhocasual.entity.person.User
import com.carinhocasual.db

//reference https://github.com/AndreasVolkmann/ktor-auth-jwt-sample

fun Route.jwtAuthorization () {
    val issuer = "http://carinhocasual.com/login"
    val secret = "MYSECRETKEY"
    val validityInMs = 36_000_000 * 24
    val algorithm = Algorithm.HMAC256 (secret)

    val verifier: JWTVerifier = JWT
        .require (algorithm)
        .withIssuer(issuer)
        .build ()
        
    fun getExpirationTime () = Date (System.currentTimeMillis() + validityInMs)
    
    fun makeToken (user: User): String = JWT.create ()
        .withSubject("Authorization")
        .withIssuer (issuer)
        .withClaim("userID", user.getId())
        .withExpiresAt(getExpirationTime ())
        .sign (algorithm)
    
    fun makeJwtVerifier (issuer: String, audience: String): JWTVerifier = JWT
        .require (algorithm)
        .withAudience (audience)
        .withIssuer (issuer)
        .build ()

    application.install (Authentication) {
        jwt {
            verifier (verifier)
            realm = "carinhocasual.com"
            validate {
                val payload = it.payload.getClaim("userId")
                val user = db.users.find { it.getId () == payload.toString () }

                if (user != null) {
                    UserIdPrincipal (payload.toString ())
                } else {
                    null
                }
            }
        }
    }
}