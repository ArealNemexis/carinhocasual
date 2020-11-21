package com.carinhocasual.interfaces

interface IPerson {
    fun getId (): String?
    fun getName (): String?
    fun getEmail (): String?
    fun getPass (): String?
    fun getPhone (): String?
    fun getBrithday (): String?
    fun setId ()
    fun setName (name: String)
    fun setEmail (email: String)
    fun setPassword (password: String)
    fun setPhone (phone: String)
    fun setBirthday (birthday: String)
}