package com.carinhocasual.user

class User(
        var name:String,
        var phone:String,
        var email:String,
        var birthday: String,
        var id:String = "")
{
    var lastLocal: Local = Local()

//    var id:String = UUID.randomUUID().toString();




}