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

      get ("/genders") {
        call.response.status (HttpStatusCode.OK)
        call.respond (app.genders)
      }

      get ("/gender/{id}") {
        //get de first object that matches gender id.
        val responseGender: Gender? = app.genders.firstOrNull {it.id == call.parameters ["id"]}

        if (responseGender == null) {
          call.response.status (HttpStatusCode.NotFound)
        } else {
          call.response.status (HttpStatusCode.OK)
          call.respond (responseGender)
        }
      }

      post ("/gender") {
        //create new gender object with data coming from request
        //it is only consuming the field "label" from the request
        val newGender = call.receive <Gender> ()

        //the field id is being setting here, based on the label
        newGender.id = UUID.nameUUIDFromBytes(newGender.label.toLowerCase().toByteArray()).toString ()

        //search for this id in app.genders and only stores it if it was not found.
        val alreadyExists: Boolean = app.genders.any {it.id == newGender.id}

        if (alreadyExists) {
          call.response.status (HttpStatusCode.Conflict)
        } else {
          //store the new gender obj in app.gender
          app.genders.add (newGender)
          call.response.status (HttpStatusCode.Created)
          call.respond (newGender)
        }
      }

      delete ("/gender/{id}") {
        //search to see if given id belongs to a object in app.genders and store this object in genderToDelete.
        val genderToDelete: Gender? = app.genders.firstOrNull {it.id == call.parameters ["id"]}

        //proceed only if object was found.
        if (genderToDelete == null) {
          call.response.status (HttpStatusCode.NotFound)
        } else {
          //remove object found from app.genders.
          app.genders.remove (genderToDelete)
          call.response.status (HttpStatusCode.OK)
        }
      }

      put ("/gender/{id}") {
        //search to see if given id belongs to any object in app.genders
        var genderToUpdate: Gender? = app.genders.firstOrNull {it.id == call.parameters ["id"]}

        //process only if objecta was found.
        if (genderToUpdate == null) {
          call.response.status (HttpStatusCode.NotFound)
        } else {
          //removes the found object
          app.genders.remove (genderToUpdate)

          //and creates a new one
          //i decided to use this way, because the gender.id id based on the label and update the label is the same
          //as update the id, so the same as delete and create a new object
          var newGender: Gender = call.receive <Gender> ()
          newGender.id = UUID.nameUUIDFromBytes (newGender.label.toLowerCase ().toByteArray ()).toString ()

          app.genders.add (newGender)
          call.response.status (HttpStatusCode.OK)
        }
      }
    }
  }.start(wait = true)
}
