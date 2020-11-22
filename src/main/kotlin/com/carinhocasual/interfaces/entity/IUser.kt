package com.carinhocasual.interfaces.entity

interface IUser {
    fun getInterests (): MutableList <String>
    fun getGender (): String?
    fun getSexualOrientation (): String?
    fun addInterest (interestId: String)
    fun setGender (genderId: String)
    fun setSexualOrientation (sOrientationId: String)
}