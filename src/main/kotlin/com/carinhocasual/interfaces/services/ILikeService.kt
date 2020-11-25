package com.carinhocasual.interfaces.services

import com.carinhocasual.resource.Response
import com.carinhocasual.entity.like.Like

interface ILikeService: IService {
    fun persists (like: Like): Response
    fun validate (like: Like): Response
}