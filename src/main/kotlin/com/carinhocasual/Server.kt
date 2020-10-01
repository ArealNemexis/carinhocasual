package com.carinhocasual

import io.ktor.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.auth.*
import io.ktor.routing.*

import com.carinhocasual.database.InMemoryDB
import com.carinhocasual.routes.genderRoutes
import com.carinhocasual.routes.interestRoutes
import com.carinhocasual.routes.sexualOrientationRoutes
import com.carinhocasual.routes.userRoutes
import com.carinhocasual.routes.authRoute

val db = InMemoryDB ()

fun main () {
    val server = embeddedServer (Netty, 8080) {
        install (ContentNegotiation) {
            gson {
                setPrettyPrinting ()
            }
        }
        
        authRoute ()
        genderRoutes ()
        sexualOrientationRoutes()
        interestRoutes ()
        userRoutes ()
    }

    server.start (wait = true)
}
