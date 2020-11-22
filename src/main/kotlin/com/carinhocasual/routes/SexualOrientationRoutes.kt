// package com.carinhocasual.routes

// import io.ktor.application.*
// import io.ktor.http.*
// import io.ktor.request.*
// import io.ktor.response.*
// import io.ktor.routing.*

// import com.carinhocasual.service.SexualOrientationService
// import com.carinhocasual.entity.sexualOrientation.SexualOrientation
// import com.carinhocasual.resource.Response

// val osService = SexualOrientationService ()

// fun Application.sexualOrientationRoutes () {
//     routing {
//         get ("/sexualorientations") {
//             val sexualOrientations = osService.getAll ()
//             call.respond (Response (sexualOrientations))
//         }
        
//         get ("/sexualorientation/{id}") {
//             val found = osService.getOne (call.parameters ["id"].toString ())

//             if (found != null) {
//                 call.respond (HttpStatusCode.Found, Response (found))
//             } else {
//                 call.respond (HttpStatusCode.NotFound, Response ("Object not found"))
//             }
//         }

//         post ("/sexualorientation") {
//             val newSexualOrientation: SexualOrientation = call.receive <SexualOrientation> ()
//             val validation: Int = osService.validate (newSexualOrientation)

//             if (validation == 409) {
//                 call.respond (HttpStatusCode.Conflict, Response ("This label already exists"))
//             } else if (validation == 401) {
//                 call.respond (HttpStatusCode.BadRequest, Response ("Bad request"))
//             } else {
//                 osService.persist(newSexualOrientation)
//                 call.respond (HttpStatusCode.Created, Response (newSexualOrientation))
//             }
//         }

//         delete ("/sexualorientation/{id}") {
//             val parameter: String = call.parameters ["id"].toString ()
//             val removed = osService.remove (parameter)

//             if (removed) {
//                 call.respond (HttpStatusCode.OK, Response ("Object deleted"))
//             } else {
//                 call.respond (HttpStatusCode.NotFound, Response ("Object not found"))
//             }
//         }

//         put ("/sexualorientation/{id}") {
//             val param = call.parameters ["id"].toString ()
//             val newSexualOrientation = call.receive <SexualOrientation> ()

//             val replaced = osService.replace (param, newSexualOrientation)

//             if (replaced == 200) {
//                 call.respond (HttpStatusCode.OK, Response (newSexualOrientation))
//             } else if (replaced == 409) {
//                 call.respond (HttpStatusCode.Conflict, Response ("This label already exists"))
//             } else if (replaced == 401) {
//                 call.respond (HttpStatusCode.BadRequest, Response ("Bad Request"))
//             }
//         }
//     }
// }
