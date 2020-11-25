package com.carinhocasual.service.LikeService

import com.carinhocasual.interfaces.services.ILikeService
import com.carinhocasual.resource.Response
import com.carinhocasual.entity.like.Like
import com.carinhocasual.resource.exceptions.*
import java.util.UUID
import com.carinhocasual.db

class LikeService (): ILikeService {

    override fun validate (like: Like): Response {
        like.setId ()
        val searchForMatch = db.likes.firstOrNull { it.getUserLiked () == like.getUserLiking () && it.getUserLiking () == like.getUserLiked () }
        val searchForDelete = db.likes.firstOrNull {
            it.getId () == UUID.nameUUIDFromBytes (like.getUserLiked ().toByteArray ()).toString () ||
            it.getId () == UUID.nameUUIDFromBytes (like.getUserLiking ().toByteArray ()).toString ()
        }

        db.likes.remove (searchForDelete)

        if (searchForMatch == null) {
            val newLike = Like (like.getUserLiking (), like.getUserLiked ())
            newLike.setId ()
            db.likes.remove (like)
            return persists (newLike)
        } else {
            val newLike = Like (like.getUserLiking (), like.getUserLiked ())
            newLike.setId ()
            newLike.setIsMatch ()
            db.likes.remove (like)
            return persists (newLike)
        }
    }

    override fun getAll (): Response {
        val response = db.likes
        return Response (response, 200)
    }

    override fun remove (id: String): Response {
        val like = db.likes.firstOrNull { it.getId () == id }
        if (like == null) {
            throw NotFoundException()
        } else {
            db.likes.remove (like)
            return Response (like, 200)
        }
    }

    override fun getOne (id: String): Response {
        val like = db.likes.firstOrNull { it.getId () == id }
        if (like == null) throw NotFoundException() else return Response (like, 200)
    }

    override fun persists (like: Like): Response {
        db.likes.add (like)
        return Response (like, 201)
    }
}