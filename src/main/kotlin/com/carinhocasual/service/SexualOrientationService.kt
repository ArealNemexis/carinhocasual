package com.carinhocasual.service

import java.util.UUID

import com.carinhocasual.entity.sexualOrientation.SexualOrientation
import com.carinhocasual.db

class SexualOrientationService: Service () {
    override fun validate (obj: SexualOrientation): Int {
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

    override fun exist (id: String): Boolean {

    }

    override fun getOne (id: String): SexualOrientation? {
        val sexualOrientation = db.sexualOrientations.firstOrNull { it.getId () == id }
        return sexualOrientation
    }

    override fun getAll (): MutableList <SexualOrientation> {
        return db.sexualOrientations
    }

    override fun persist (obj: SexualOrientation) {
        db.sexualOrientations.add (obj)
    }

    fun remove (id: String): Boolean {

    }

    fun replace (id: String, sexualOrientation: SexualOrientation): Int {

    }
}