package com.carinhocasual.entity.sexualOrientation

import com.carinhocasual.interfaces.entity.ISexualOrientation

class SexualOrientation (private var id: String, private var label: String): ISexualOrientation {
    override fun getId () = id
    override fun getLabel () = label

    override fun setId (id: String) {
        this.id = id
    }

    override fun setLabel (label: String) {
        this.label = label
    }
}
