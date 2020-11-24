package com.carinhocasual.interfaces.services

import com.carinhocasual.entity.person.Person
import com.carinhocasual.resource.Response

interface IPersonService: IService{
    fun validate (obj: Person)
    fun persist (obj: Person): Response
    fun replace (id: String, obj: Person): Response
}