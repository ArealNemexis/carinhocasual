package com.carinhocasual.service

import java.util.UUID

import com.carinhocasual.entity.gender.Gender
import com.carinhocasual.db

class GenderService (): Service {
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

    override fun exist (id: String): Boolean {

    }

    override fun getOne (id: String): Gender? {
        val gender = db.genders.firstOrNull { it.getId () == id }
        return gender
    }

    override fun getAll (): MutableList <Gender> {
        return db.genders
    }

    override fun persist (obj: Gender) {
        db.genders.add (obj)
    }

    fun remove (id: String): Boolean {
    }

    fun replace (id: String, gender: Gender): Int {

    }
}

