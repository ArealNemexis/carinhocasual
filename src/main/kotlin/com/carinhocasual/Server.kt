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
import com.carinhocasual.app.App
import com.carinhocasual.gender.Gender


val app = App ()

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
        call.response.status(HttpStatusCode.OK)
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

      put("/user/edit/{id}"){
        val id = call.parameters["id"]
        val putParams = call.receive<User>()
        println("searching User with $id")
        var returnUser = Users.filter { it.id == id }
        if(returnUser.isEmpty()){
          println("User not found")
          call.response.status(HttpStatusCode.NotFound)
        }else{
          returnUser[0].editAll(putParams)
          call.response.status(HttpStatusCode.OK)
        }
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
