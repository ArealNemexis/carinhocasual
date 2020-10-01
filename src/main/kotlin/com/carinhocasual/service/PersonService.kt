package com.carinhocasual.service

import com.carinhocasual.entity.person.Person
import com.carinhocasual.entity.person.User
import com.carinhocasual.entity.person.Admin
import com.carinhocasual.db
import java.util.UUID

//implementar polimorfismo para classes herdadas de Person
//implementar objetos de resposta para efetuar abstração depois, vai ficar bem mais fácil devolver
//para o controller

//melhorar o nome das variaveis de obj

class PersonService () {
    fun validate (obj: Person): Int {
        if (obj.getName () == "" || obj.getName () == null ||
            obj.getEmail () == "" || obj.getEmail () == null ||
            obj.getPass () == "" || obj.getPass () == null
        ){
            return 401
        } else {
            val userIdConlict = db.users.firstOrNull { it.getId () == obj.getId () }
            val userEmailConflict = db.users.firstOrNull {it.getEmail () == obj.getEmail ()}

            //setar um while nessa condição
            if (userIdConlict != null) obj.setId ()

            if (userEmailConflict != null) return 409 else return 201
        }
    }

    fun exist (id: String): Boolean {
        val user = db.users.firstOrNull { it.getId() == id }

        if (user != null) {
            return true
        } else {
            return false
        }
    }

    fun getOne (id: String): Person? {
        val user = db.users.firstOrNull { it.getId () == id }
        return user
    }

    fun getAll (): MutableList <Person> {
        return db.users
    }

    fun persist (obj: Person) {
        db.users.add (obj)
    }

    fun remove (id: String): Boolean {
        val user = db.users.firstOrNull { it.getId () == id }
        
        if (user != null) {
            db.users.remove (user)
            return true
        }  else {
            return false
        }
    }

    fun replace (id: String, user: Person): Int {
        val isValid = validate(user)

        if (isValid == 409) {
            return 409
        } else if (isValid == 401) {
            return 401
        } else {
            remove (id)
            persist (user)
            return 200
        }
    }
}
