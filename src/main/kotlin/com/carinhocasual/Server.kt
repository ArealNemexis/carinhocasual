package com.carinhocasual

import com.carinhocasual.user.Local
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.carinhocasual.user.User
import java.util.*

val Users = mutableListOf<User>()
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
        println("Returning all Users")
        call.respond(Users)
      }

      get("/users/{id}"){
        val id = call.parameters["id"]
        println("searching User with $id")
        var returnUser = Users.filter { it.id == id }
        if(returnUser.isEmpty()){
          println("User not found")
          call.response.status(HttpStatusCode.NotFound)
        }else{
          call.response.status(HttpStatusCode.OK)
          call.respond(returnUser)
        }
      }

      post("/users/register") {
        val postParameters = call.receive<User>()
        var newId = UUID.randomUUID().toString();
        Users.add(User(postParameters.name, postParameters.phone, postParameters.email, postParameters.birthday, id = newId))


        call.respond(newId)
      }

      post("/users/location/{id}"){
        val id = call.parameters["id"]
        val localParams = call.receive<Local>()
        println("searching User with $id")
        var returnUser = Users.filter { it.id == id }
        if(returnUser.isEmpty()){
          println("User not found")
          call.response.status(HttpStatusCode.NotFound)
        }else{
          println("User found, changing location")
          returnUser[0].lastLocal = Local( localParams.last_longitude, localParams.last_latitude)
          call.response.status(HttpStatusCode.OK)
        }
      }
    }
  }.start(wait = true)
}
