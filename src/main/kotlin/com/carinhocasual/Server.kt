package com.carinhocasual

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import user.User
import kotlin.reflect.typeOf


val Users = arrayListOf<User>()
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

      get("/users"){
        call.respond(Users)
      }

      post("/users/register") {
        val postParameters = call.receive<User>()
        println("new user $postParameters")
        Users.add(postParameters)

        call.respond(postParameters)
      }
    }
  }.start(wait = true)
}
