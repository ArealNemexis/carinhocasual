package com.carinhocasual.user

import com.carinhocasual.utils.uuidGenerate

open class People(
    name: String?,
    phone: String?,
    email: String?,
    birthday: String?,
){
    var name:String? =  null
    var phone:String? = null
    var email:String? = null
    var birthday:String? = null
    var id:String? = null

    init {
        this.name = name
        this.phone= phone
        this.email = email
        this.birthday = birthday
        this.id = uuidGenerate()
    }
}

//    fun editAll(target:People){
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