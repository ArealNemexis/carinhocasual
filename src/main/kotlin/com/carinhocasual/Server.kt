package com.carinhocasual

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.carinhocasual.sexual_orientation.SexualOrientation
import com.carinhocasual.interests.Interest
import com.carinhocasual.app.App
import io.ktor.request.*
import io.ktor.response.*
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
