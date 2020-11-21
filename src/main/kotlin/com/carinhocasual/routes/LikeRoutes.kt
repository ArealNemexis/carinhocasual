package com.carinhocasual.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

import com.carinhocasual.resource.Response
import com.carinhocasual.db
import com.carinhocasual.entity.like.Like
import com.carinhocasual.entity.like.Match
import com.carinhocasual.entity.like.SuperLike
import com.carinhocasual.entity.person.Person

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

                var jaDeuMatch : Boolean = false

                for(match in db.matches){
                    if(match.first == liked.getId()){
                        if(match.second == liker.getId()){
                            jaDeuMatch = true
                            break
                        }
                    }
                    else if (match.first == liker.getId()){
                        if (match.second == liked.getId()){
                            jaDeuMatch = true
                            break
                        }
                    }
                }

                if(retorno && !jaDeuMatch){
                    db.matches.add(Match(liker.getId(), liked.getId()))
                    matched = true
                }
                if(jaDeuMatch){
                    call.respond(Response("${liker.getId()} e ${liked.getId()} já deram match anteriormente"))
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
        get("likes/description"){
            val retorno = Like(Person())

            call.respond( Response(retorno.getDescription()))
        }
        get("likes/super/description"){
            val retorno = SuperLike(Person())

            call.respond( Response(retorno.getDescription()))
        }
        delete("/like/{id}/{likedid}"){
            val disliker = personService.getOne (call.parameters["id"].toString())
            val disliked = personService.getOne (call.parameters["likedid"].toString())

            var isDisliked = false

            if (disliker != null && disliked != null) {
                for(item in disliker.likes){
                    if(item.likedUser.getId() == disliked.getId()){
                        isDisliked = true
                    }
                }
            }

            if(isDisliked){
                for(match in db.matches){
                    if (disliker != null && disliked != null) {
                        if(match.first == disliker.getId() || match.second == disliker.getId()){
                            if(match.second == disliked.getId() || match.first == disliked.getId()){
                                db.matches.remove(match)
                            }
                        }
                    }
                }

                call.respond (Response ("Sucess"))
            }else{
                call.respond (HttpStatusCode.NotFound, Response (mutableListOf(disliker, disliked)))
            }

            /*if(isDisliked != null){
                disliker?.likes?.remove(disliked)

                for(match in db.matches){
                    if (disliker != null && disliked != null) {
                        if(match.first == disliker.getId() || match.second == disliker.getId()){
                            if(match.second == disliked.getId() || match.first == disliked.getId()){
                                db.matches.remove(match)
                            }
                        }
                    }
                }

                call.respond (Response ("Sucess"))
            }else{
                call.respond (HttpStatusCode.NotFound, Response (isDisliked))
            }*/

        }
    }
}
