package com.carinhocasual.service

import java.util.UUID

import com.carinhocasual.entity.interest.Interest
import com.carinhocasual.db

class InterestService () {
    fun validate (obj: Interest): Int {
        var interests = db.interests

        obj.setId (UUID.nameUUIDFromBytes(((obj.getLabel ()).toLowerCase ()).toByteArray ()).toString())

        val interest = interests.firstOrNull { it.getId () == obj.getId () }

        if (interest != null) {
            return 409
        } else if (obj.getLabel () == "") {
            return 401
        } else {
            return 201
        }
    }

    fun exist (id: String): Boolean {
        val interest = db.interests.firstOrNull { it.getId() == id }

        if (interest != null) {
            return true
        } else {
            return false
        }
    }

    fun getOne (id: String): Interest? {
        val interest = db.interests.firstOrNull { it.getId () == id }
        return interest
    }

    fun getAll (): MutableList <Interest> {
        return db.interests
    }

    fun persist (obj: Interest) {
        db.interests.add (obj)
    }

    fun remove (id: String): Boolean {
        val interest = db.interests.firstOrNull { it.getId () == id }
        
        if (interest != null) {
            db.interests.remove (interest)
            return true
        }  else {
            return false
        }
    }

    fun replace (id: String, interest: Interest): Int {
        val isValid = validate(interest)

        if (isValid == 409) {
            return 409
        } else if (isValid == 401) {
            return 401
        } else {
            remove (id)
            persist (interest)
            return 200
        }
    }
}

