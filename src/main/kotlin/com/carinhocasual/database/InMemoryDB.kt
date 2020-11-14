package com.carinhocasual.database

import com.carinhocasual.entity.gender.Gender
import com.carinhocasual.entity.interest.Interest
import com.carinhocasual.entity.like.Match
import com.carinhocasual.entity.sexualOrientation.SexualOrientation
import com.carinhocasual.entity.person.Person

class InMemoryDB () {
    val genders: MutableList <Gender> = mutableListOf <Gender> ()
    val interests: MutableList <Interest> = mutableListOf <Interest> ()
    val sexualOrientations: MutableList <SexualOrientation> = mutableListOf <SexualOrientation> ()
    val users: MutableList <Person> = mutableListOf <Person> ()
    val matches: MutableList <Match> = mutableListOf <Match> ()
}