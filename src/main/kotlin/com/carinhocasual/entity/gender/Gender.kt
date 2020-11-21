package com.carinhocasual.entity.gender
import com.carinhocasual.interfaces.IGender

class Gender (private var id: String, private var label: String): IGender {
    override fun getId () = id
    override fun getLabel () = label

    override fun setId (id: String) {
        this.id = id
    }

    override fun setLabel (label: String) {
        this.label = label
    }
}