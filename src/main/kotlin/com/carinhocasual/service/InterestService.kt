package com.carinhocasual.service

import java.util.UUID

import com.carinhocasual.entity.interest.Interest
import com.carinhocasual.db

class InterestService: Service () {
    override fun validate (obj: Interest): Int {
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

    }

    override fun getOne (id: String): Interest? {
        val interest = db.interests.firstOrNull { it.getId () == id }
        return interest
    }

    override fun getAll (): MutableList <Interest> {
        return db.interests
    }

    override fun persist (obj: Interest) {
        db.interests.add (obj)
    }

    fun remove (id: String): Boolean {

    }

    fun replace (id: String, interest: Interest): Int {

    }
}

