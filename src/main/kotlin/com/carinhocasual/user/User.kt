package com.carinhocasual.user

import com.carinhocasual.gender.Gender
import com.carinhocasual.sexual_orientation.SexualOrientation
import com.carinhocasual.utils.uuidGenerate

class User(
        name:String,
        phone:String,
        email:String,
        birthday: String,
        id:String = "",
        ): People(name, phone,email,birthday, id)
{
    var lastLocal: Local = Local()
    var sexualOrientations = mutableListOf<SexualOrientation>()
    var gender = mutableListOf<Gender>()
    var filter:UserFilter = UserFilter(id = uuidGenerate())

    fun setLongitude(newLongitude:Float){
        lastLocal.lastLongitude = newLongitude
    }
    fun setLatitude(newLatitude:Float){
        lastLocal.lastLatitude = newLatitude
    }

//    fun editAll(target:User){
//        if(target.name != null && target.name != ""){
//            this.name = target.name
//        }
//        if(target.phone != null && target.phone != ""){
//            this.phone = target.phone
//        }
//        if(target.email != null && target.email != ""){
//            this.email = target.email
//        }
//        if(target.birthday != null && target.birthday != ""){
//            this.birthday = target.birthday
//        }
//    }

}