package com.carinhocasual.database

import com.carinhocasual.entity.gender.Gender
import com.carinhocasual.entity.interest.Interest
import com.carinhocasual.entity.sexualOrientation.SexualOrientation
import com.carinhocasual.entity.person.Person
import com.carinhocasual.entity.like.Like

class InMemoryDB () {
    val genders: MutableList <Gender> = mutableListOf <Gender> ()
    val interests: MutableList <Interest> = mutableListOf <Interest> ()
    val sexualOrientations: MutableList <SexualOrientation> = mutableListOf <SexualOrientation> ()
    val users: MutableList <Person> = mutableListOf <Person> ()
    val likes: MutableList <Like> = mutableListOf <Like> ()
}