package com.carinhocasual.service

import java.util.UUID
import com.carinhocasual.db
import com.carinhocasual.interfaces.services.ISexualOrientationService
import com.carinhocasual.entity.sexualOrientation.SexualOrientation
import com.carinhocasual.resource.Response
import com.carinhocasual.resource.exceptions.*

class SexualOrientationService (): ISexualOrientationService {
    override fun validate (obj: SexualOrientation) {
        obj.setId (UUID.nameUUIDFromBytes(((obj.getLabel ()).toLowerCase ()).toByteArray ()).toString())
        val gender = db.sexualOrientations.firstOrNull { it.getId () == obj.getId () }

        if (gender != null) {
            throw ConflictException ()
        } else if (obj.getLabel () == "") {
            throw BadRequestException ()
        }
    }

    override fun getOne (id: String): Response {
        val obj = db.sexualOrientations.firstOrNull { it.getId () == id }
        if (obj == null) {
            throw NotFoundException ()
        } else {
            return Response (obj, 200)
        }
    }

    override fun getAll (): Response {
        return Response (db.sexualOrientations, 200)
    }

    override fun persist (obj: SexualOrientation): Response {
        try {
            validate (obj)
        } catch (e: BadRequestException) {
            return Response (obj, 400)
        } catch (e: ConflictException) {
            return Response (obj, 409)
        }

        db.sexualOrientations.add (obj)
        return Response (obj, 201)
    }

    override fun remove (id: String): Response {
        val obj = db.sexualOrientations.firstOrNull { it.getId () == id }

        if (obj == null) {
            throw NotFoundException ()
        } else {
            db.sexualOrientations.remove (obj)
            return Response (obj, 200)
        }
    }

    override fun replace (id: String, obj: SexualOrientation): Response {
        try {
            validate (obj)
        } catch (e: ConflictException) {
            return Response (obj, 409)
        } catch (e: BadRequestException) {
            return Response (obj, 400)
        }

        remove (id)
        persist(obj)
        return Response(obj, 200)
    }
}