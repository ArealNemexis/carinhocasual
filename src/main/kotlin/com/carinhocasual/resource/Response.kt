package com.carinhocasual.resource

class Response (var responseObject: Any, private var statusCode: Int) {
    fun getStatusCode () = statusCode
}
