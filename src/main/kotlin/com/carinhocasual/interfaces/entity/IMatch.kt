package com.carinhocasual.interfaces.entity

interface IMatch {
    fun getUser1 (): String
    fun getUser2 (): String
    fun getUser1Like2 (): Boolean
    fun getUser2Like1 (): Boolean
    fun setUser1Like2 (state: Boolean)
    fun setUser2Like1 (state: Boolean)
    fun isMatched (): Boolean
}