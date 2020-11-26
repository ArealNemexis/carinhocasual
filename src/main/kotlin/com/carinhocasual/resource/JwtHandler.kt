package com.carinhocasual.resource

import com.auth0.jwt.*
import com.auth0.jwt.algorithms.*
import io.ktor.auth.*
import io.ktor.auth.authentication

class JwtHandler () 
    private val secret: String = System.getenv ("JWT_S_HASH")
    private val issuer: String = "api.carinhocasual.com"
    private val algorithm = Algorithm.HMAC256(secret)

    fun getToken (userId: String): String = JWT.create ()
    .withSubject("Authorization")
    .withIssuer (issuer)
    .withClaim("user_id", userId)
    .withExpiresAt(getExpirationTime ())
    .sign (algorithm) {

    

    fun getVerifier () {

    }

}