package com.carinhocasual.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

import com.carinhocasual.resource.Response
import com.carinhocasual.db
import com.carinhocasual.entity.like.Match

fun Application.LikeRoutes () {
    routing {
        get ("/likes/{id}") {
            val likeList = personService.getOne (call.parameters["id"].toString())
            if(likeList != null){
                call.respond (Response (likeList.likes))
            }else{
                call.respond (HttpStatusCode.NotFound, Response ("Object not found"))
            }
        }
        get("/like/{id}/{likedid}"){
            val liker = personService.getOne (call.parameters["id"].toString())
            val liked = personService.getOne (call.parameters["likedid"].toString())

            if( liked != null && liker != null){
                if(liker.isLiked(liked)){
                    call.respond(Response("${liker.getId()} ja deu like em ${liked.getId()}"))
                }

                val retorno = liker.addLike(liked)
                var matched = false
                if(retorno){
                    db.matches.add(Match(liker.getId(), liked.getId()))
                    matched = true
                }
                if(matched){
                    call.respond(Response("${liker.getId()} e ${liked.getId()} deram match"))
                }else{
                    call.respond(Response("${liker.getId()} deu um like em ${liked.getId()}"))
                }
            }else{
                call.respond(HttpStatusCode.NotFound, Response ("Object not found"))
            }
        }
    }
}
