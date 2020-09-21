package com.carinhocasual.user

class User(
        var name:String,
        var phone:String,
        var email:String,
        var birthday: String,
        var id:String = "")
{
    var lastLocal: Local = Local()

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

//    var id:String = UUID.randomUUID().toString();




}