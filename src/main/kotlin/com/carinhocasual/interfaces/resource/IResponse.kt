package com.carinhocasual.interfaces.resource

import com.carinhocasual.resource.Response
import io.ktor.http.HttpStatusCode

interface IResponse {
    fun getStatusCode (): HttpStatusCode
}