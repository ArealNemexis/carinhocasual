package com.carinhocasual.user

import com.carinhocasual.gender.Gender
import com.carinhocasual.sexual_orientation.SexualOrientation
import com.carinhocasual.utils.uuidGenerate

class User (
    name: String?,
    phone: String?,
    email: String?,
    birthday: String?,
): People (name, phone, email, birthday)
{
    var lastLocal: Local = Local()
    var sexualOrientations = mutableListOf<SexualOrientation>()
    var gender = mutableListOf<Gender>()
    var filter:UserFilter = UserFilter(id = uuidGenerate())

    private fun setLongitude(newLongitude:Float){
        lastLocal.lastLongitude = newLongitude
    }
    private fun setLatitude(newLatitude:Float){
        lastLocal.lastLatitude = newLatitude
    }

    fun setLocal(newLongitude:Float, newLatitude:Float){
        setLongitude(newLongitude)
        setLatitude(newLatitude)
    }

    fun editAll(target:User){
        if(target.name != null && target.name != ""){
            this.name = target.name
        }
        if(target.phone != null && target.phone != ""){
            this.phone = target.phone
        }
        if(target.email != null && target.email != ""){
            this.email = target.email
        }
        if(target.birthday != null && target.birthday != ""){
            this.birthday = target.birthday
        }
    }

}