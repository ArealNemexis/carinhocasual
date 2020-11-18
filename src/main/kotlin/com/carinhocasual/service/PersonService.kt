package com.carinhocasual.service

import com.carinhocasual.entity.person.Person
import com.carinhocasual.entity.person.User
import com.carinhocasual.entity.person.Admin
import com.carinhocasual.db
import java.util.UUID

class PersonService : Service {
    override fun validate (obj: Person): Int {
        if (obj.getName () == "" || obj.getName () == null ||
                obj.getEmail () == "" || obj.getEmail () == null ||
                obj.getPass () == "" || obj.getPass () == null
        ){
            return 401
        } else {
            val userIdConlict = db.users.firstOrNull { it.getId () == obj.getId () }
            val userEmailConflict = db.users.firstOrNull {it.getEmail () == obj.getEmail ()}

            if (userIdConlict != null) obj.setId ()

        }
    }

    override fun exist (id: String) Boolean{

    }

    override fun getOne (id: String): Person? {
        val user = db.users.firstOrNull { it.getId () == id }
        return user
    }

    override  fun getAll (): MutableList <Person> {
        return db.users
    }

    override fun persist (obj: Person) {
        db.users.add (obj)
    }

    override fun remove (id: String): Boolean {
    }

    override fun replace (id: String, user: Person): Int {
    }
}
