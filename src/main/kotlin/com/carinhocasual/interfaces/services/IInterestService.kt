package com.carinhocasual.interfaces.services

import com.carinhocasual.entity.interest.Interest
import com.carinhocasual.resource.Response

interface IInterestService: IService {
    fun validate (obj: Interest)
    fun persist (obj: Interest): Response
    fun replace (id: String, obj: Interest): Response
}