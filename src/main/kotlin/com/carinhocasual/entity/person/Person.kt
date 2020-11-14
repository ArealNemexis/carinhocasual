package com.carinhocasual.entity.person

import com.carinhocasual.entity.like.Like
import com.carinhocasual.entity.like.SuperLike
import java.util.UUID

open class Person (
    private var id: String = UUID.randomUUID().toString (),
    private var name: String? = null,
    private var email: String? = null,
    private var pass: String? = null,
    private var phone: String? = null,
    private var birthday: String? = null,
    private var token: String? = null,
    private var status: String? = "active",
    var likes: MutableList<Like> = mutableListOf <Like> (),
    var superLikes: MutableList<SuperLike> = mutableListOf <SuperLike> (),
    //var picture: Picture? = defaultPicture (não implementado ainda)
) {
    fun getId () = id
    fun getName () = name
    fun getEmail () = email
    fun getPass () = pass
    fun getPhone () = phone
    fun getBrithday () = birthday
    fun getToken () = token

    fun setId () {
        this.id = UUID.randomUUID().toString ()
    }

    fun setName (name: String) {
        this.name = name
    }

    fun setEmail (email: String) {
        this.email = email
    }

    fun setPassword (password: String) {
        this.pass = password
    }

    fun setPhone (phone: String) {
        this.phone = phone
    }

    fun setBirthday (birthday: String) {
        this.birthday = birthday
    }

    fun setToken (token: String) {
        this.token = token
    }

    fun isLiked(liked:Person): Boolean{
        var like = likes.firstOrNull { it.likedUser.getId() == liked.getId()}
        if(like != null){
            return true
        }
        return false
    }

    fun addLike(liked: Person): Boolean{
        var like = isLiked(liked)

        if(!like) {
            likes.add(Like(liked))
        }

        var match = liked.isLiked(this)
        if(match){
            return true
        }
        return false
    }


    fun isSuperLiked(liked:Person): Boolean{
        var like = superLikes.firstOrNull { it.likedUser.getId() == liked.getId()}
        if(like != null){
            return true
        }
        return false
    }

    fun addSuperLike(liked: Person): Boolean{
        var like = isLiked(liked)

        if(!like) {
            superLikes.add(SuperLike(liked))
        }

        //var match = liked.isLiked(this)
        if(liked.isLiked(this) or liked.isSuperLiked(this)){
            return true
        }
        return false
    }
}