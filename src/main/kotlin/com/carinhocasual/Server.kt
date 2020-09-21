package com.carinhocasual

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.carinhocasual.app.App
import com.carinhocasual.gender.Gender
import io.ktor.request.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.util.*

val app = App ()

fun main() {
  embeddedServer(Netty, 8080) {
    routing {

      install(ContentNegotiation) {
        gson {
          setPrettyPrinting()
        }
      }

      get("/") {
        call.response.status(HttpStatusCode.OK)
        call.respondText("<h1>Hello Kotlin!</h1>", ContentType.Text.Html)
      }

      post ("/gender") {
        //create new gender object with data coming from request
        //it is only consuming the field "label" from the request
        val newGender = call.receive <Gender> ()

        //the field uid is being setting here, based on the label
        newGender.uid = UUID.nameUUIDFromBytes(newGender.label.toLowerCase().toByteArray()).toString ()

        //store the new gender obj in app.gender
        app.genders.add (newGender)
        call.response.status(HttpStatusCode.Created)
        call.respond (newGender)
      }

      get ("/genders") {
        call.response.status(HttpStatusCode.OK)
        call.respond (app.genders)
      }
    }
  }.start(wait = true)
}
