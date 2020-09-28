package com.carinhocasual.service

import java.util.UUID

import com.carinhocasual.entity.gender.Gender
import com.carinhocasual.db

class GenderService () {
    fun validate (obj: Gender): Int {
        var genders = db.genders

        obj.setId (UUID.nameUUIDFromBytes(((obj.getLabel ()).toLowerCase ()).toByteArray ()).toString())

        val gender = genders.firstOrNull { it.getId () == obj.getId () }

        if (gender != null) {
            return 409
        } else if (obj.getLabel () == "") {
            return 401
        } else {
            return 201
        }
    }

    fun exist (id: String): Boolean {
        val gender = db.genders.firstOrNull { it.getId() == id }

        if (gender != null) {
            return true
        } else {
            return false
        }
    }

    fun getOne (id: String): Gender? {
        val gender = db.genders.firstOrNull { it.getId () == id }
        return gender
    }

    fun getAll (): MutableList <Gender> {
        return db.genders
    }

    fun persist (obj: Gender) {
        db.genders.add (obj)
    }

    fun remove (id: String): Boolean {
        val gender = db.genders.firstOrNull { it.getId () == id }
        
        if (gender != null) {
            db.genders.remove (gender)
            return true
        }  else {
            return false
        }
    }

    fun replace (id: String, gender: Gender): Int {
        val isValid = validate(gender)

        if (isValid == 409) {
            return 409
        } else if (isValid == 401) {
            return 401
        } else {
            remove (id)
            persist (gender)
            return 200
        }
    }
}

