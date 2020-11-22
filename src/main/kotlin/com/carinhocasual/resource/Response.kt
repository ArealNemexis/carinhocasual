package com.carinhocasual.resource
import io.ktor.http.*

class Response (var responseObject: Any, private var statusCode: Int){
    fun getStatusCode (): HttpStatusCode = HttpStatusCode.fromValue(statusCode)
}
