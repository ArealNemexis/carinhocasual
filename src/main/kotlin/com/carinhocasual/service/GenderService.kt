package com.carinhocasual.service

import java.util.UUID
import com.carinhocasual.db
import com.carinhocasual.interfaces.services.IGenderService
import com.carinhocasual.entity.gender.Gender
import com.carinhocasual.resource.Response

class GenderService (): IGenderService {
    override fun validate (obj: Gender): Int {
        obj.setId (UUID.nameUUIDFromBytes(((obj.getLabel ()).toLowerCase ()).toByteArray ()).toString())
        val gender = db.genders.firstOrNull { it.getId () == obj.getId () }

        if (gender != null) {
            return 409
        } else if (obj.getLabel () == "") {
            return 401
        } else {
            return 201
        }
    }

    override fun getOne (id: String): Response {
        val obj = db.genders.firstOrNull { it.getId () == id }
        if (obj == null) {
            return Response ("Not Found", 404)
        } else {
            return Response (obj, 200)
        }
    }

    override fun getAll (): Response {
        return Response (db.genders, 200)
    }

    override fun persist (obj: Gender): Response {
        var validationReturn = validate (obj)

        if (validationReturn == 400) {
            return Response (obj, 400)
        } else if (validationReturn == 409) {
            return Response (obj, 409)
        } else {
            db.genders.add (obj)
            return Response (obj, 201)
        }
    }

    override fun remove (id: String): Response {
        return Response ("Not implemented", 200)
    }
}

