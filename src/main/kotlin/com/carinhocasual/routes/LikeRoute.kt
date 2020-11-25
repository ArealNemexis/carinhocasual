package com.carinhocasual.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

import com.carinhocasual.service.LikeService.LikeService
import com.carinhocasual.entity.like.Like
import com.carinhocasual.resource.Response
import com.carinhocasual.resource.exceptions.*
import java.lang.*

val ls = LikeService ()

fun Application.likeRoutes () {
    routing {
        get ("/likes") {
            val response = ls.getAll ()
            call.respond (response.getStatusCode (), response)
        }

        get ("/like/{id}") {
            try {
                val id = call.parameters ["id"].toString ()
                val response = ls.getOne(id)
                call.respond (response.getStatusCode (), response)
            } catch (e: NotFoundException) {
                call.respond (HttpStatusCode.NotFound)
            }
        }

        post ("/like") {
            try {
                val newLike: Like = call.receive <Like> ()
                val response = ls.validate (newLike)
                call.respond (response.getStatusCode (), response)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest)
            }

        }

        delete ("/like/{id}") {
            try {
                val id = call.parameters ["id"].toString ()
                val response = ls.getOne (id)
                call.respond (response.getStatusCode (), response)
            } catch (e: NotFoundException) {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}