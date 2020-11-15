package com.carinhocasual.entity.like

import com.carinhocasual.entity.person.Person

class SuperLike(
    likedUser: Person,
    description: String = "Like é a maneira do(a) crush saber o seu interesse."
): Like(likedUser, description){

    override fun getDescription(): String {
        return "Super" + super.getDescription() + " Obs: SuperLike possui um custo para o uso, mas garante uma notificação para seu crush."
    }
}