package com.carinhocasual.entity.like

import com.carinhocasual.entity.person.Person

open class Like(
   var likedUser: Person,
   private var description : String = "Like é a maneira do(a) crush saber o seu interesse"
){
    open fun getDescription() : String{
        return description
    }

    fun setDescription(description: String){
        this.description = description
    }
}