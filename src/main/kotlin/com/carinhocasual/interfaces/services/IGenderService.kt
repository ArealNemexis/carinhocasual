package com.carinhocasual.interfaces.services

import com.carinhocasual.entity.gender.Gender
import com.carinhocasual.resource.Response

interface IGenderService: IService {
    fun validate (obj: Gender)
    fun persist (obj: Gender): Response
    fun replace (id: String, obj: Gender): Response
}