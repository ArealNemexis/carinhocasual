package com.carinhocasual.interfaces.services

import com.carinhocasual.entity.sexualOrientation.SexualOrientation
import com.carinhocasual.resource.Response

interface ISexualOrientationService: IService {
    fun validate (obj: SexualOrientation)
    fun persist (obj: SexualOrientation): Response
    fun replace (id: String, obj: SexualOrientation): Response
}