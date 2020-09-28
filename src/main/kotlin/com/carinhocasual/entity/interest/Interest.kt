package com.carinhocasual.entity.interest


class Interest (private var id: String, private var label: String) {
    fun getId () = id
    fun getLabel () = label

    fun setId (id: String) {
        this.id = id
    }

    fun setLabel (label: String) {
        this.label = label
    }
}