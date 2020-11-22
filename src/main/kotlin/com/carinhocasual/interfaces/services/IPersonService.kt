package com.carinhocasual.interfaces.services

import com.carinhocasual.entity.person.Person

interface IPersonService: IService{
    fun getOne (): Person?
}