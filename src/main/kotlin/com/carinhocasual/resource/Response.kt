package com.carinhocasual.resource

import io.ktor.http.HttpStatusCode
import com.carinhocasual.interfaces.resource.IResponse

class Response (var responseObject: Any, private var statusCode: Int): IResponse {
    override fun getStatusCode (): HttpStatusCode = HttpStatusCode.fromValue(statusCode)
}
