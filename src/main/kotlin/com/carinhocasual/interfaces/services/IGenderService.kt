package com.carinhocasual.interfaces.services

import com.carinhocasual.entity.gender.Gender
import com.carinhocasual.resource.Response

interface IGenderService: IService {
    fun validate (obj: Gender): Int
    fun persist (obj: Gender): Response
}