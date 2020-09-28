package com.carinhocasual.entity.person

import java.util.UUID

open class Person (
    private var id: String = UUID.randomUUID().toString (),
    private var name: String? = null,
    private var email: String? = null,
    private var pass: String? = null,
    private var phone: String? = null,
    private var birthday: String? = null,
    private var token: String? = null,
    private var status: String? = "active"
    //var picture: Picture? = defaultPicture (não implementado ainda)
) {
    fun getId () = id
    fun getName () = name
    fun getEmail () = email
    fun getPass () = pass
    fun getPhone () = phone
    fun getBrithday () = birthday
    fun getToken () = token

    fun setId () {
        this.id = UUID.randomUUID().toString ()
    }

    fun setName (name: String) {
        this.name = name
    }

    fun setEmail (email: String) {
        this.email = email
    }

    fun setPassword (password: String) {
        this.pass = password
    }

    fun setPhone (phone: String) {
        this.phone = phone
    }

    fun setBirthday (birthday: String) {
        this.birthday = birthday
    }

    fun setToken (token: String) {
        this.token = token
    }
}