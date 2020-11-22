package com.carinhocasual.interfaces.entity

interface IGender {
    fun getId (): String
    fun getLabel (): String
    fun setId (id: String)
    fun setLabel (label: String)
}