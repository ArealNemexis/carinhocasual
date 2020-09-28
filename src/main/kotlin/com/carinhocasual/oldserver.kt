// package com.carinhocasual

// import io.ktor.application.*
// import io.ktor.features.*
// import io.ktor.gson.*
// import io.ktor.http.*
// import io.ktor.request.*
// import io.ktor.response.*
// import io.ktor.routing.*
// import io.ktor.server.engine.*
// import io.ktor.server.netty.*
// import java.util.*

// import com.carinhocasual.routes.genderRoutes
// import com.carinhocasual.utils.uuidGenerate
// import com.carinhocasual.database.InMemoryDB


// // val app = App ()
// //val Users = mutableListOf<User>()

// val db = InMemoryDB ()

// fun main() {
//     embeddedServer(Netty, 8080) {
//         routing {
//             install(ContentNegotiation) {
//                 gson {
//                     setPrettyPrinting()
//                 }
//             }

//             genderRoutes ()            

//             get("/") {
//                 call.response.status(HttpStatusCode.OK)
//                 call.respondText("<h1>Hello Kotlin!</h1>", ContentType.Text.Html)
//             }

            // get("/users") {
            //     println("Returning all Users")
            //     call.respond(app.Users)
            // }

            // post("/users/register") {
            //     val postParameters = call.receive<User>()
            //     var newUser = User(postParameters.name, postParameters.phone, postParameters.email, postParameters.birthday)
            //     app.Users.add(newUser)
            //     call.respond(newUser.id!!)
            // }

            // get("/users/{id}") {
            //     val id = call.parameters["id"]
            //     println("searching User with id:$id")
            //     var returnUser = app.Users.filter { it.id == id }
            //     if (returnUser.isEmpty()) {
            //         println("User not found")
            //         call.response.status(HttpStatusCode.NotFound)
            //     } else {
            //         call.response.status(HttpStatusCode.OK)
            //         call.respond(returnUser)
            //     }
            // }



            // post("/users/location/{id}") {
            //     val id = call.parameters["id"]
            //     val localParams = call.receive<Local>()
            //     println("searching User with $id")
            //     var returnUser = app.Users.filter { it.id == id }
            //     if (returnUser.isEmpty()) {
            //         println("User not found")
            //         call.response.status(HttpStatusCode.NotFound)
            //     } else {
            //         println("User found, changing location")
            //         returnUser[0].setLocal(localParams.lastLongitude, localParams.lastLatitude)
            //         call.response.status(HttpStatusCode.OK)
            //     }
            // }

            // put("/user/edit/{id}") {
            //     val id = call.parameters["id"]
            //     val putParams = call.receive<User>()
            //     println("searching User with $id")
            //     var returnUser = app.Users.filter { it.id == id }
            //     if (returnUser.isEmpty()) {
            //         println("User not found")
            //         call.response.status(HttpStatusCode.NotFound)
            //     } else {
            //         returnUser[0].editAll(putParams)
            //         call.response.status(HttpStatusCode.OK)
            //     }
            // }

            // post("/sexual_orientation") {

            //     val newSexualOrientation = call.receive <SexualOrientation>()

            //     newSexualOrientation.id = UUID.nameUUIDFromBytes(newSexualOrientation.label.toLowerCase().toByteArray()).toString ()

            //     val alreadyExists: Boolean = app.sexualOrientation.any {it.id == newSexualOrientation.id}

            //     if (alreadyExists) {
            //         call.response.status (HttpStatusCode.Conflict)
            //     } else {
            //         app.sexualOrientation.add(newSexualOrientation)
            //         call.response.status(HttpStatusCode.Created)
            //         call.respond(newSexualOrientation)
            //     }

            // }

            // get("/sexual_orientation") {
            //     call.response.status (HttpStatusCode.OK)
            //     call.respond (app.sexualOrientation)
            // }

            // get ("/sexual_orientation/{id}") {
            //     val responseSexualOrientation: SexualOrientation? = app.sexualOrientation.firstOrNull {it.id == call.parameters ["id"]}

            //     if (responseSexualOrientation == null) {
            //         call.response.status (HttpStatusCode.NotFound)
            //     } else {
            //         call.response.status (HttpStatusCode.OK)
            //         call.respond (responseSexualOrientation)
            //     }
            // }

            // put ("/sexual_orientation/{id}") {

            //     var sexualOrientationToUpdate: SexualOrientation? = app.sexualOrientation.firstOrNull {it.id == call.parameters ["id"]}

            //     if (sexualOrientationToUpdate == null) {
            //         call.response.status (HttpStatusCode.NotFound)
            //     } else {
            //         app.sexualOrientation.remove(sexualOrientationToUpdate)

            //         var newSexualOrientation: SexualOrientation = call.receive<SexualOrientation>()
            //         newSexualOrientation.id = UUID.nameUUIDFromBytes(newSexualOrientation.label.toLowerCase().toByteArray()).toString()

            //         app.sexualOrientation.add(newSexualOrientation)
            //         call.response.status(HttpStatusCode.OK)
            //     }
            // }

            // delete ("/sexual_orientation/{id}") {

            //     val sexualOrientationToDelete: SexualOrientation? = app.sexualOrientation.firstOrNull {it.id == call.parameters ["id"]}

            //     if (sexualOrientationToDelete == null) {
            //         call.response.status (HttpStatusCode.NotFound)
            //     } else {

            //         app.sexualOrientation.remove (sexualOrientationToDelete)
            //         call.response.status (HttpStatusCode.OK)
            //     }
            // }

//         }


//     }.start(wait = true)
// }