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
            // 

//         }


//     }.start(wait = true)
// }