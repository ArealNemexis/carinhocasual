package com.carinhocasual.entity.person

import com.carinhocasual.entity.gender.Gender
import com.carinhocasual.entity.interest.Interest
import com.carinhocasual.entity.sexualOrientation.SexualOrientation
import com.carinhocasual.entity.person.Location
import com.carinhocasual.entity.like.Like
import com.carinhocasual.entity.like.SuperLike

class User (
    // private var interests: MutableList <Interest> = mutableListOf <Interest> (),
    // private var gender: Gender? = null,
    // private var sOrientation: SexualOrientation? = null,
    // private var location: Location? = null
    private var interests: MutableList <String> = mutableListOf <String> (),
    private var gender: String? = null,
    private var sOrientation: String? = null,
    private var location: String? = null
): Person () {
    fun getInterests () = interests
    fun getGender () = gender
    fun getSexualOrientation () = sOrientation

    fun addInterest (interestId: String) {
        this.interests.add (interestId)
    }

    fun setGender (genderId: String) {
        this.gender = genderId
    }

    fun setSexualOrientation (sOrientationId: String) {
        this.sOrientation = sOrientationId
    }
}
