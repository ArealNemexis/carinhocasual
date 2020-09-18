package com.carinhocasual

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
  embeddedServer(Netty, 8080) {
    routing {
      install(ContentNegotiation) {
        gson {
          setPrettyPrinting()
        }
      }

      get("/") {
        call.respondText("<h1>Hello Kotlin!</h1>", ContentType.Text.Html)
      }

    }
  }.start(wait = true)
}
