package com.carinhocasual.service

import java.util.UUID
import com.carinhocasual.db
import com.carinhocasual.interfaces.services.IPersonService
import com.carinhocasual.entity.person.Person
import com.carinhocasual.entity.person.User
import com.carinhocasual.entity.person.Admin
import com.carinhocasual.resource.Response
import com.carinhocasual.resource.exceptions.*

class PersonService (): IPersonService {
    override fun validate (obj: Person) {
        if (obj.getName () == "" || obj.getName () == null ||
            obj.getEmail() == "" || obj.getEmail() == null ||
            obj.getPass () == "" || obj.getPass () == null )
        {
            throw BadRequestException ()
        } else {
            val userEmailConflict = db.users.firstOrNull { it.getEmail () == obj.getEmail () }

            if (userEmailConflict != null) {
                throw ConflictException ()
            }
        }
    }

    override fun getOne (id: String): Response {
        val user = db.users.firstOrNull { it.getId () == id }
        
        if (user == null) {
            throw NotFoundException ()
        } else {
            return Response (user, 200)
        }
    }

    override fun getAll (): Response {
        return Response (db.users, 200)
    }

    override fun persist (obj: Person): Response {
        try {
            validate (obj)
        } catch (e: BadRequestException) {
            return Response (obj, 400)
        } catch (e: ConflictException) {
            return Response (obj, 409)
        }

        db.users.add (obj)
        return Response (obj, 201)
    }

    override fun remove (id: String): Response {
        val obj = db.users.firstOrNull { it.getId () == id }

        if (obj == null) {
            throw NotFoundException ()
        } else {
            db.users.remove (obj)
            return Response (obj, 200)
        }
    }

    override fun replace (id: String, obj: Person): Response {
        try {
            validate (obj)
        } catch (e: ConflictException) {
            return Response (obj, 409)
        } catch (e: BadRequestException) {
            return Response (obj, 400)
        }

        remove (id)
        persist(obj)
        return Response (obj, 200)
    }

}
