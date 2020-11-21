package com.carinhocasual.entity.person

import java.util.UUID
import com.carinhocasual.interfaces.IPerson

open class Person (
    private var id: String = UUID.randomUUID().toString (),
    private var name: String? = null,
    private var email: String? = null,
    private var pass: String? = null,
    private var phone: String? = null,
    private var birthday: String? = null,
    private var status: String? = "active",
    //var picture: Picture? = defaultPicture (não implementado ainda)
): IPerson {
    override fun getId () = id
    override fun getName () = name
    override fun getEmail () = email
    override fun getPass () = pass
    override fun getPhone () = phone
    override fun getBrithday () = birthday

    override fun setId () {
        this.id = UUID.randomUUID().toString ()
    }

    override fun setName (name: String) {
        this.name = name
    }

    override fun setEmail (email: String) {
        this.email = email
    }

    override fun setPassword (password: String) {
        this.pass = password
    }

    override fun setPhone (phone: String) {
        this.phone = phone
    }

    override fun setBirthday (birthday: String) {
        this.birthday = birthday
    }
}