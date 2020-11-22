package com.carinhocasual.interfaces.services

import com.carinhocasual.resource.Response

interface IService {
    fun remove (id: String): Response
    fun getOne (id: String): Response
    fun getAll (): Response
}