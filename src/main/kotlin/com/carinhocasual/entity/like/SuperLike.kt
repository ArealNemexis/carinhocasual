package com.carinhocasual.entity.like

import com.carinhocasual.entity.person.Person

class SuperLike(
    likedUser: Person,
    var priority: Boolean = true
): Like(likedUser)