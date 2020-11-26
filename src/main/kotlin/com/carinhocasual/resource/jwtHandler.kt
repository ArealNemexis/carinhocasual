package com.carinhocasual.resource

import com.auth0.jwt.*
import com.auth0.jwt.algorithms.*
import java.util.*

class JwtHandler {
    val secret: String = System.getenv ("JWT_SECRET")?: "deusa_lissandra"
    val algorithm = Algorithm.HMAC256 (secret)
    val issuer = "http://carinhocasual.com/login"
    val jwtVerifier: JWTVerifier = JWT
            .require (algorithm)
            .withIssuer (issuer)
            .build ()

    fun makeToken (userId: String): String = JWT.create ()
        .withIssuer (issuer)
        .withClaim("userID", userId)
        .sign (algorithm)
}