package com.carinhocasual.entity.match

import com.carinhocasual.interfaces.entity.IMatch

class Match (
    private var user1: String,
    private var user2: String,
    private var user1Like2: Boolean = false,
    private var user2Like1: Boolean = false
): IMatch {
    override fun getUser1 (): String = user1
    override fun getUser2 (): String = user2
    override fun getUser1Like2 (): Boolean = user1Like2
    override fun getUser2Like1 (): Boolean = user2Like1

    override fun setUser1Like2 (state: Boolean) {
        this.user1Like2 = state
    }

    override fun setUser2Like1 (state: Boolean) {
        this.user2Like1 = state
    }

    override fun isMatched (): Boolean {
        return user1Like2 && user2Like1;
    }
}