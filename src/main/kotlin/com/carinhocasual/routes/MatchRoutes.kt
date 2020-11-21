package com.carinhocasual.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

import com.carinhocasual.resource.Response
import com.carinhocasual.db
import com.carinhocasual.entity.like.Match
fun Application.MatchRoutes () {
    routing {
        get("/matches"){
            call.respond (Response (db.matches))
        }

        get("/matches/{id}"){
            var userMatches : MutableList <Match> = mutableListOf <Match> ()
            var userId : String = call.parameters["id"].toString()

            db.matches.forEach {
                if(userId == it.first){
                    userMatches.add(Match(userId, it.second))
                }else if(userId == it.second){
                    userMatches.add(Match(userId, it.first))
                }
            }
            call.respond(Response(userMatches))
        }
    }
}
