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
import com.carinhocasual.user.User
import java.util.*
import com.carinhocasual.app.App
import com.carinhocasual.gender.Gender
import com.carinhocasual.sexual_orientation.SexualOrientation
import com.carinhocasual.interests.Interest
import com.carinhocasual.user.Local
import com.carinhocasual.utils.uuidGenerate


val app = App ()

//val Users = mutableListOf<User>()
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

            get("/users") {
                println("Returning all Users")
                call.respond(app.Users)
            }

            post("/users/register") {
                val postParameters = call.receive<User>()
                var newUser = User(postParameters.name, postParameters.phone, postParameters.email, postParameters.birthday)
                app.Users.add(newUser)
                call.respond(newUser.id!!)
            }

            get("/users/{id}") {
                val id = call.parameters["id"]
                println("searching User with id:$id")
                var returnUser = app.Users.filter { it.id == id }
                if (returnUser.isEmpty()) {
                    println("User not found")
                    call.response.status(HttpStatusCode.NotFound)
                } else {
                    call.response.status(HttpStatusCode.OK)
                    call.respond(returnUser)
                }
            }



            post("/users/location/{id}") {
                val id = call.parameters["id"]
                val localParams = call.receive<Local>()
                println("searching User with $id")
                var returnUser = app.Users.filter { it.id == id }
                if (returnUser.isEmpty()) {
                    println("User not found")
                    call.response.status(HttpStatusCode.NotFound)
                } else {
                    println("User found, changing location")
                    returnUser[0].setLocal(localParams.lastLongitude, localParams.lastLatitude)
                    call.response.status(HttpStatusCode.OK)
                }
            }

            put("/user/edit/{id}") {
                val id = call.parameters["id"]
                val putParams = call.receive<User>()
                println("searching User with $id")
                var returnUser = app.Users.filter { it.id == id }
                if (returnUser.isEmpty()) {
                    println("User not found")
                    call.response.status(HttpStatusCode.NotFound)
                } else {
                    returnUser[0].editAll(putParams)
                    call.response.status(HttpStatusCode.OK)
                }
            }


            get("/genders") {
                call.response.status(HttpStatusCode.OK)
                call.respond(app.genders)
            }

            get("/gender/{id}") {
                //get de first object that matches gender id.
                val responseGender: Gender? = app.genders.firstOrNull { it.id == call.parameters["id"] }

                if (responseGender == null) {
                    call.response.status(HttpStatusCode.NotFound)
                } else {
                    call.response.status(HttpStatusCode.OK)
                    call.respond(responseGender)
                }
            }

            post("/gender") {
                //create new gender object with data coming from request
                //it is only consuming the field "label" from the request
                val newGender = call.receive<Gender>()

                //the field id is being setting here, based on the label
                newGender.id = UUID.nameUUIDFromBytes(newGender.label.toLowerCase().toByteArray()).toString()

                //search for this id in app.genders and only stores it if it was not found.
                val alreadyExists: Boolean = app.genders.any { it.id == newGender.id }

                if (alreadyExists) {
                    call.response.status(HttpStatusCode.Conflict)
                } else {
                    //store the new gender obj in app.gender
                    app.genders.add(newGender)
                    call.response.status(HttpStatusCode.Created)
                    call.respond(newGender)
                }
            }

            delete("/gender/{id}") {
                //search to see if given id belongs to a object in app.genders and store this object in genderToDelete.
                val genderToDelete: Gender? = app.genders.firstOrNull { it.id == call.parameters["id"] }

                //proceed only if object was found.
                if (genderToDelete == null) {
                    call.response.status(HttpStatusCode.NotFound)
                } else {
                    //remove object found from app.genders.
                    app.genders.remove(genderToDelete)
                    call.response.status(HttpStatusCode.OK)
                }
            }

            put("/gender/{id}") {
                //search to see if given id belongs to any object in app.genders
                var genderToUpdate: Gender? = app.genders.firstOrNull { it.id == call.parameters["id"] }

                //process only if objecta was found.
                if (genderToUpdate == null) {
                    call.response.status(HttpStatusCode.NotFound)
                } else {
                    //removes the found object
                    app.genders.remove(genderToUpdate)

                    //and creates a new one
                    //i decided to use this way, because the gender.id id based on the label and update the label is the same
                    //as update the id, so the same as delete and create a new object
                    var newGender: Gender = call.receive<Gender>()
                    newGender.id = UUID.nameUUIDFromBytes(newGender.label.toLowerCase().toByteArray()).toString()

                    app.genders.add(newGender)
                    call.response.status(HttpStatusCode.OK)
                }
            }

            get("/") {
                call.response.status(HttpStatusCode.OK)
                call.respondText("<h1>Hello Kotlin!</h1>", ContentType.Text.Html)
            }

            post("/sexual_orientation") {

                val newSexualOrientation = call.receive <SexualOrientation>()

                newSexualOrientation.id = UUID.nameUUIDFromBytes(newSexualOrientation.label.toLowerCase().toByteArray()).toString ()

                val alreadyExists: Boolean = app.sexualOrientation.any {it.id == newSexualOrientation.id}

                if (alreadyExists) {
                    call.response.status (HttpStatusCode.Conflict)
                } else {
                    app.sexualOrientation.add(newSexualOrientation)
                    call.response.status(HttpStatusCode.Created)
                    call.respond(newSexualOrientation)
                }

            }

            get("/sexual_orientation") {
                call.response.status (HttpStatusCode.OK)
                call.respond (app.sexualOrientation)
            }

            get ("/sexual_orientation/{id}") {
                val responseSexualOrientation: SexualOrientation? = app.sexualOrientation.firstOrNull {it.id == call.parameters ["id"]}

                if (responseSexualOrientation == null) {
                    call.response.status (HttpStatusCode.NotFound)
                } else {
                    call.response.status (HttpStatusCode.OK)
                    call.respond (responseSexualOrientation)
                }
            }

            put ("/sexual_orientation/{id}") {

                var sexualOrientationToUpdate: SexualOrientation? = app.sexualOrientation.firstOrNull {it.id == call.parameters ["id"]}

                if (sexualOrientationToUpdate == null) {
                    call.response.status (HttpStatusCode.NotFound)
                } else {
                    app.sexualOrientation.remove(sexualOrientationToUpdate)

                    var newSexualOrientation: SexualOrientation = call.receive<SexualOrientation>()
                    newSexualOrientation.id = UUID.nameUUIDFromBytes(newSexualOrientation.label.toLowerCase().toByteArray()).toString()

                    app.sexualOrientation.add(newSexualOrientation)
                    call.response.status(HttpStatusCode.OK)
                }
            }

            delete ("/sexual_orientation/{id}") {

                val sexualOrientationToDelete: SexualOrientation? = app.sexualOrientation.firstOrNull {it.id == call.parameters ["id"]}

                if (sexualOrientationToDelete == null) {
                    call.response.status (HttpStatusCode.NotFound)
                } else {

                    app.sexualOrientation.remove (sexualOrientationToDelete)
                    call.response.status (HttpStatusCode.OK)
                }
            }

            post("/interests") {

                val newInterest = call.receive <Interest>()

                newInterest.id = UUID.nameUUIDFromBytes(newInterest.label.toLowerCase().toByteArray()).toString ()

                val alreadyExists: Boolean = app.interest.any {it.id == newInterest.id}

                if (alreadyExists) {
                    call.response.status (HttpStatusCode.Conflict)
                } else {
                    app.interest.add(newInterest)
                    call.response.status(HttpStatusCode.Created)
                    call.respond(newInterest)
                }

            }

            get("/interests") {
                call.response.status (HttpStatusCode.OK)
                call.respond (app.interest)
            }

            get ("/interests/{id}") {
                val responseInterest: Interest? = app.interest.firstOrNull {it.id == call.parameters ["id"]}

                if (responseInterest == null) {
                    call.response.status (HttpStatusCode.NotFound)
                } else {
                    call.response.status (HttpStatusCode.OK)
                    call.respond (responseInterest)
                }
            }

            put ("/interests/{id}") {

                var interestToUpdate: Interest? = app.interest.firstOrNull {it.id == call.parameters ["id"]}

                if (interestToUpdate == null) {
                    call.response.status (HttpStatusCode.NotFound)
                } else {
                    app.interest.remove(interestToUpdate)

                    var newInterest: Interest = call.receive<Interest>()
                    newInterest.id = UUID.nameUUIDFromBytes(newInterest.label.toLowerCase().toByteArray()).toString()

                    app.interest.add(newInterest)
                    call.response.status(HttpStatusCode.OK)
                }
            }

            delete ("/interests/{id}") {

                val interestToDelete: Interest? = app.interest.firstOrNull {it.id == call.parameters ["id"]}

                if (interestToDelete == null) {
                    call.response.status (HttpStatusCode.NotFound)
                } else {

                    app.interest.remove (interestToDelete)
                    call.response.status (HttpStatusCode.OK)
                }
            }
        }


    }.start(wait = true)
}