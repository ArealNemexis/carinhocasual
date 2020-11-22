package com.carinhocasual.entity.person

import com.carinhocasual.entity.gender.Gender
import com.carinhocasual.entity.interest.Interest
import com.carinhocasual.entity.sexualOrientation.SexualOrientation
import com.carinhocasual.entity.person.Location
import com.carinhocasual.interfaces.entity.IUser

class User (
    private var interests: MutableList <String> = mutableListOf <String> (),
    private var gender: String? = null,
    private var sOrientation: String? = null,
    private var location: String? = null
): Person (), IUser {
    override fun getInterests () = interests
    override fun getGender () = gender
    override fun getSexualOrientation () = sOrientation

    override fun addInterest (interestId: String) {
        this.interests.add (interestId)
    }

    override fun setGender (genderId: String) {
        this.gender = genderId
    }

    override fun setSexualOrientation (sOrientationId: String) {
        this.sOrientation = sOrientationId
    }
}
