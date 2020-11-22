// package com.carinhocasual.routes

// import io.ktor.application.*
// import io.ktor.http.*
// import io.ktor.request.*
// import io.ktor.response.*
// import io.ktor.routing.*

// import com.carinhocasual.service.PersonService
// import com.carinhocasual.entity.person.Person
// import com.carinhocasual.entity.person.User
// import com.carinhocasual.entity.person.Admin
// import com.carinhocasual.resource.Response

// val personService = PersonService ()

// fun Application.userRoutes () {
//     routing {
//         get ("/users") {
//             val users = personService.getAll ()
//             call.respond (Response (users))
//         }

//         get ("/user/{id}") {
//             val found = personService.getOne (call.parameters ["id"].toString ())

//             if (found != null) {
//                 call.respond (HttpStatusCode.Found, Response (found))
//             } else {
//                 call.respond (HttpStatusCode.NotFound, Response ("Object not found"))
//             }
//         }

//         post ("/user") {
//             val newUser: User = call.receive <User> ()
//             val validation: Int = personService.validate (newUser)

//             if (validation == 409) {
//                 call.respond (HttpStatusCode.Conflict, Response ("This email already exists"))
//             } else if (validation == 401) {
//                 call.respond (HttpStatusCode.BadRequest, Response ("Bad request"))
//             } else {
//                 personService.persist(newUser)
//                 call.respond (HttpStatusCode.Created, Response (newUser))
//             }
//         }

//         delete ("/user/{id}") {
//             val parameter: String = call.parameters ["id"].toString ()
//             val removed = true

//             if (removed) {
//                 call.respond (HttpStatusCode.OK, Response ("Object deleted"))
//             } else {
//                 call.respond (HttpStatusCode.NotFound, Response ("Object not found"))
//             }
//         }

//         put ("/user/{id}") {
//             val param = call.parameters ["id"].toString ()
//             val gender = call.receive <User> ()

//             val replaced = 200

//             if (replaced == 200) {
//                 call.respond (HttpStatusCode.OK, Response (gender))
//             } else if (replaced == 409) {
//                 call.respond (HttpStatusCode.Conflict, Response ("This email already exists"))
//             } else if (replaced == 401) {
//                 call.respond (HttpStatusCode.BadRequest, Response ("Bad Request"))
//             }
//         }
//     }
// }
