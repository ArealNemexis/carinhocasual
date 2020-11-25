package com.carinhocasual

import io.ktor.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.auth.*
import io.ktor.routing.*
import io.ktor.util.*
import io.ktor.util.pipeline.*
import io.ktor.http.*
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.response.*
import io.ktor.request.httpMethod
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode

import com.carinhocasual.database.InMemoryDB
import com.carinhocasual.routes.*

val db = InMemoryDB ()

fun main () {
    val PORT: Int = System.getenv ("PORT")?.toInt ()?: 8080

    val server = embeddedServer (Netty, PORT) {
        install (ContentNegotiation) {
            gson {
                setPrettyPrinting ()
            }
        }

        install (CORS) {
            method (HttpMethod.Options)
            method (HttpMethod.Get)
            method (HttpMethod.Post)
            method (HttpMethod.Put)
            method (HttpMethod.Delete)
            header (HttpHeaders.Authorization)
            header (HttpHeaders.AccessControlAllowOrigin)
            anyHost ()
            allowCredentials = true
            allowSameOrigin = true
        }

        authRoute ()
        genderRoutes ()
        sexualOrientationRoutes()
        interestRoutes ()
        userRoutes ()
        likeRoutes ()
    }

    server.start (wait = true)
}
