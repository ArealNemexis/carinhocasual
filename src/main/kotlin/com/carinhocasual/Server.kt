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
import com.carinhocasual.routes.*
import com.carinhocasual.resource.authenticationModule

import com.carinhocasual.database.InMemoryDB

val db = InMemoryDB ()

fun main () {
    val PORT: Int = System.getenv ("PORT")?.toInt ()?: 8080 //essa val precisa ser exatamente esse nome

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

            intercept(ApplicationCallPipeline.Setup) {
                if (call.request.httpMethod == HttpMethod.Options) {
                    call.response.header("Access-Control-Allow-Origin", "*")
                    call.response.header("Access-Control-Allow-Headers", "*")
                    call.respond(HttpStatusCode.OK)
                    return@intercept finish()
                }
            }
        }

<<<<<<< HEAD
        

=======
        routing {
            get ("/") {
                call.respondRedirect ("/login")
            }
        }
        
        authenticationModule ()
>>>>>>> 98bfcd1fb0d22d43f02f2d0b113d27c3576bb215
        authRoute ()
        genderRoutes ()
        sexualOrientationRoutes()
        interestRoutes ()
        userRoutes ()
        likeRoutes ()
    }

    server.start (wait = true)
}
