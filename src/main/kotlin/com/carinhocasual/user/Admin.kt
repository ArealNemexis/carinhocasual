package com.carinhocasual.user

class Admin(
    name:String,
    phone:String,
    email:String,
    birthday: String,
    id:String = ""): People(name, phone,email,birthday){
    var password:String? = null
}