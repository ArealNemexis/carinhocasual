package com.carinhocasual.entity.sexualOrientation

class SexualOrientation (private var id: String, private var label: String) {
    fun getId () = id
    fun getLabel () = label

    fun setId (id: String) {
        this.id = id
    }

    fun setLabel (label: String) {
        this.label = label
    }
}
