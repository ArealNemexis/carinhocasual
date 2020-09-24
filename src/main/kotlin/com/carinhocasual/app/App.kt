package com.carinhocasual.app

import com.carinhocasual.gender.Gender
import com.carinhocasual.interests.Interest
import com.carinhocasual.sexual_orientation.SexualOrientation
import com.carinhocasual.user.Admin
import com.carinhocasual.user.People
import com.carinhocasual.user.User

class App {
    val Users = mutableListOf<User>()
    var genders = mutableListOf <Gender> ()
    var sexualOrientation = mutableListOf<SexualOrientation>()
    var interest = mutableListOf<Interest>()

    fun setNewGender(people: People, genderLabel:String): Int{
        if(people is Admin){
            genders.add(Gender(genderLabel))
            return 1
        }
        return 0
    }

    fun setNewOrientation(people: People, sexualOrientationLabel: String): Int{
        if (people is Admin){
            sexualOrientation.add(SexualOrientation(sexualOrientationLabel))
            return 1
        }
        return 0
    }

    fun setNewInterest(people: People, interestLabel: String):Int{
        if (people is Admin){
            interest.add(Interest(interestLabel))
            return 1
        }
        return 0
    }
}
