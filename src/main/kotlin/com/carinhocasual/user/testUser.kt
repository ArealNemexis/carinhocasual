package com.carinhocasual.user

import java.util.*

fun main(){
    var users = mutableListOf<User>()
    var new = User("Lucas Ferreira de Araujo", "+5561992614682", "cobrinha@carinhocasual.com", "09-04-2000");

//    println(new.birthday)

    while(true){
        println("Type the User name: ")
        var name:String = readLine()!!
        println("Type the User phone number: ")
        var phone:String = readLine()!!
        println("Type the User Email")
        var email:String = readLine()!!
        println("Type the User born day")
        var birthday:String = readLine()!!

        var id = UUID.randomUUID().toString()

        users.add(User(name,phone, email, birthday, id=id))

        print("Want add a new User? Yes/No:")
        var op:String = readLine()!!
        println(op.toUpperCase())
        if(op.toUpperCase() == "N" || op.toUpperCase() == "NO"){
            break
        }
    }
    users.forEach { user ->
        println("----------------------------")
        println(user.id)
        println(user.name)
        println(user.phone)
        println(user.email)
        println("----------------------------")
    }
//    println(new.id)
//    println(new.name)
//    println(new.phone)
//    println(new.email)
//    println(new.last_latitude)
//    println(new.last_longitude)
}