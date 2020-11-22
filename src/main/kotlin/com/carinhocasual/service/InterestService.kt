package com.carinhocasual.service

import java.util.UUID
import com.carinhocasual.db
import com.carinhocasual.interfaces.services.IInterestService
import com.carinhocasual.entity.interest.Interest
import com.carinhocasual.resource.Response
import com.carinhocasual.resource.exceptions.*

class InterestService (): IInterestService {
    override fun validate (obj: Interest) {
        obj.setId (UUID.nameUUIDFromBytes(((obj.getLabel ()).toLowerCase ()).toByteArray ()).toString())
        val interest = db.interests.firstOrNull { it.getId () == obj.getId () }

        if (interest != null) {
            throw ConflictException()
        } else if (obj.getLabel () == "") {
            throw BadRequestException ()
        }
    }

    override fun getOne (id: String): Response {
        val obj = db.interests.firstOrNull { it.getId () == id }
        
        if (obj == null) {
            throw NotFoundException()
        } else {
            return Response (obj, 200)
        }
    }

    override fun getAll (): Response {
        return Response (db.interests, 200)
    }

    override fun persist (obj: Interest): Response {
        try {
            validate (obj)
        } catch (e: BadRequestException) {
            return Response (obj, 400)
        } catch (e: ConflictException) {
            return Response (obj, 409)
        }

        db.interests.add (obj)
        return Response (obj, 201)
    }

    override fun remove (id: String): Response {
        val obj = db.interests.firstOrNull { it.getId () == id }

        if (obj == null) {
            throw NotFoundException ()
        } else {
            db.interests.remove (obj)
            return Response (obj, 200)
        }
    }

    override fun replace (id: String, obj: Interest): Response {

        try {
            validate (obj)
        } catch (e: ConflictException) {
            return Response (obj, 409)
        } catch (e: BadRequestException) {
            return Response (obj, 400)
        }

        remove (id)
        persist(obj)
        return Response (obj, 200)
    }
}

