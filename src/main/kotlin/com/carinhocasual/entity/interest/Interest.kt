package com.carinhocasual.entity.interest

import com.carinhocasual.interfaces.IInterest

class Interest (private var id: String, private var label: String): IInterest {
    override fun getId () = id
    override fun getLabel () = label

    override fun setId (id: String) {
        this.id = id
    }

    override fun setLabel (label: String) {
        this.label = label
    }
}