package com.carinhocasual.service

import java.util.UUID

import com.carinhocasual.entity.sexualOrientation.SexualOrientation
import com.carinhocasual.db

class SexualOrientationService {
    fun validate (obj: SexualOrientation): Int {
        var sexualOrientations = db.sexualOrientations

        obj.setId (UUID.nameUUIDFromBytes(((obj.getLabel ()).toLowerCase ()).toByteArray ()).toString())

        val sexualOrientation = sexualOrientations.firstOrNull { it.getId () == obj.getId () }

        if (sexualOrientation != null) {
            return 409
        } else if (obj.getLabel () == "") {
            return 401
        } else {
            return 201
        }
    }

    fun getOne (id: String): SexualOrientation? {
        val sexualOrientation = db.sexualOrientations.firstOrNull { it.getId () == id }
        return sexualOrientation
    }

    fun getAll (): MutableList <SexualOrientation> {
        return db.sexualOrientations
    }

    fun persist (obj: SexualOrientation) {
        db.sexualOrientations.add (obj)
    }

    fun remove (obj: String) {}
}