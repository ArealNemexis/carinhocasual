package com.carinhocasual.user

open class People(
    name: String,
    phone: String,
    email: String,
    birthday: String,
    id: String = "",
)

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