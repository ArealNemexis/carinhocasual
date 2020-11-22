package com.carinhocasual.interfaces.services

import com.carinhocasual.entity.interest.Interest

interface IInterestService: IService {
    fun getOne (): Interest?
}