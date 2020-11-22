package com.carinhocasual.interfaces.services

import com.carinhocasual.entity.sexualOrientation.SexualOrientation

interface ISexualOrientationService: IService {
    fun getOne (): SexualOrientation
}