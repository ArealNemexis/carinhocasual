package com.carinhocasual.entity.like

import java.util.UUID
import com.carinhocasual.db

data class Like (
    private var userLiking: String,
    private var userLiked: String
) {
    private var id: String? = null
    var isMatch: Boolean = false

    fun getId () = id
    fun getUserLiking () = userLiking
    fun getUserLiked () = userLiked
    fun getIsMatch () = isMatch
    
    fun setUserLiking (userLiking: String) { this.userLiking = userLiking }
    fun setUserLiked (userLiked: String) { this.userLiked = userLiked }
    fun setIsMatch () { this.isMatch = true }
    fun setId () { this.id = UUID.nameUUIDFromBytes(userLiking.toByteArray ()).toString ()}
}