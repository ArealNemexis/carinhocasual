// package com.carinhocasual.routes

// import io.ktor.application.*
// import io.ktor.http.*
// import io.ktor.request.*
// import io.ktor.response.*
// import io.ktor.routing.*

// import com.carinhocasual.service.InterestService
// import com.carinhocasual.entity.interest.Interest
// import com.carinhocasual.resource.Response

// val interestService = InterestService ()

// fun Application.interestRoutes () {
//     routing {
//         get ("/interests") {
//             val interests = interestService.getAll ()
//             call.respond (Response (interests))
//         }
        
//         get ("/interest/{id}") {
//             val found = interestService.getOne (call.parameters ["id"].toString ())

//             if (found != null) {
//                 call.respond (HttpStatusCode.Found, Response (found))
//             } else {
//                 call.respond (HttpStatusCode.NotFound, Response ("Object not found"))
//             }
//         }

//         post ("/interest") {
//             val newInterest: Interest = call.receive <Interest> ()
//             val validation: Int = interestService.validate (newInterest)

//             if (validation == 409) {
//                 call.respond (HttpStatusCode.Conflict, Response ("This label already exists"))
//             } else if (validation == 401) {
//                 call.respond (HttpStatusCode.BadRequest, Response ("Bad request"))
//             } else {
//                 interestService.persist(newInterest)
//                 call.respond (HttpStatusCode.Created, Response (newInterest))
//             }
//         }

//         delete ("/interest/{id}") {
//             val parameter: String = call.parameters ["id"].toString ()
//             val removed = interestService.remove (parameter)

//             if (removed) {
//                 call.respond (HttpStatusCode.OK, Response ("Object deleted"))
//             } else {
//                 call.respond (HttpStatusCode.NotFound, Response ("Object not found"))
//             }
//         }

//         put ("/interest/{id}") {
//             val param = call.parameters ["id"].toString ()
//             val newInterest = call.receive <Interest> ()

//             val replaced = interestService.replace (param, newInterest)

//             if (replaced == 200) {
//                 call.respond (HttpStatusCode.OK, Response (newInterest))
//             } else if (replaced == 409) {
//                 call.respond (HttpStatusCode.Conflict, Response ("This label already exists"))
//             } else if (replaced == 401) {
//                 call.respond (HttpStatusCode.BadRequest, Response ("Bad Request"))
//             }
//         }
//     }
// }
