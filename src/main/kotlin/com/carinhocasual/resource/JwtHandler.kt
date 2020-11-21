package com.carinhocasual.resource

import com.auth0.jwt.*
import com.auth0.jwt.algorithms.*

object JwtHandler {
    private val algorithm = Algorithm.HMAC256("secret_key")

    fun getToken (userId: String): String {
        val jwtToken: String = JWT.create ().withIssuer ("api.carinhocasual.com").withClaim("userId", userId).sign(algorithm)

        return jwtToken
    }
}