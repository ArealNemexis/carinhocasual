package com.carinhocasual.entity.person

class Location (private var latitude: Float, private var longitude: Float) {
    fun getLatitude () = latitude
    fun getLongitude () = longitude

    fun setLatitude (latitude: Float) {
        this.latitude = latitude
    }

    fun setLongitude (longitude: Float) {
        this.longitude = longitude
    }
}