package com.carinhocasual.service

import com.carinhocasual.db
import com.carinhocasual.entity.gender.Gender
import com.carinhocasual.entity.interest.Interest
import com.carinhocasual.entity.person.Person
import com.carinhocasual.entity.sexualOrientation.SexualOrientation
import java.util.*
import kotlin.Boolean as Boolean


interface Service {

    fun validate(){
//            obj: Person,
//            obj: Interest,
//            obj: Gender,
//            obj: SexualOrientation
//    ): Int {
//        var sexualOrientations = db.sexualOrientations
//
//        obj.setId(UUID.nameUUIDFromBytes(((obj.getLabel()).toLowerCase()).toByteArray()).toString())
//
//        val sexualOrientation = sexualOrientations.firstOrNull { it.getId() == obj.getId() }
//
//        var interests = db.interests
//
//        obj.setId(UUID.nameUUIDFromBytes(((obj.getLabel()).toLowerCase()).toByteArray()).toString())
//
//        val interest = interests.firstOrNull { it.getId() == obj.getId() }
//
//        obj.setId(UUID.nameUUIDFromBytes(((obj.getLabel()).toLowerCase()).toByteArray()).toString())
//
//        val gender = db.genders.firstOrNull { it.getId() == obj.getId() }
//
//        if (gender != null || sexualOrientation != null || interest != null) return 409 else return 201

    }


    fun exist(id: String): Boolean {
        val user = db.users.firstOrNull { it.getId() == id }
        val interest = db.interests.firstOrNull { it.getId() == id }
        val gender = db.genders.firstOrNull { it.getId() == id }
        val sexualOrientation = db.sexualOrientations.firstOrNull { it.getId() == id }

        if (user != null || interest != null || gender != null || sexualOrientation != null) {
            return true
        } else {
            return false
        }
    }


    fun getOne(id: String) {
//        val user = db.users.firstOrNull { it.getId() == id }
//        val interest = db.interests.firstOrNull { it.getId() == id }
//        val sexualOrientation = db.sexualOrientations.firstOrNull { it.getId() == id }
//        val gender = db.genders.firstOrNull { it.getId() == id }
//
//        return gender
//        return sexualOrientation
//        return interest
//        return user
    }

    fun getAll() {}

    fun persist() {}

    fun remove() {}

    fun replace(id: String, user: Person, interest: Interest, gender: Gender, sexualOrientation: SexualOrientation): Int {
        val isValidUser = validate(user)
        val isValidInterest = validate(interest)
        val isValidGender = validate(gender)
        val isValidSexualOrientatiom = validate(sexualOrientation)

        if (isValidUser == 409 || isValidInterest == 409 || isValidGender == 409 || isValidSexualOrientatiom == 409) {
            return 409
        } else if (isValidUser == 401 || isValidInterest == 401 || isValidGender == 401 || isValidSexualOrientatiom == 401) {
            return 401
        } else {
            return 200
        }
    }


    fun remove(id: String): Boolean {
        val sexualOrientation = db.sexualOrientations.firstOrNull { it.getId() == id }
        val interest = db.interests.firstOrNull { it.getId() == id }
        val gender = db.genders.firstOrNull { it.getId() == id }
        val user = db.users.firstOrNull { it.getId() == id }

        if (user != null) {
            db.users.remove(user)
            return true
        }
        if (gender != null) {
            db.genders.remove(gender)
            return true
        }
        if (interest != null) {
            db.interests.remove(interest)
            return true
        }
        if (sexualOrientation != null) {
            db.sexualOrientations.remove(sexualOrientation)
            return true
        } else {
            return false
        }
    }
}