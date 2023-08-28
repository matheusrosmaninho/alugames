package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Gamer

fun main() {
    val game1 = Gamer("Jaque", "jaque@email.com")
    val game2 = Gamer("Jeny", "jeny@email.com", "19/10/1992", "jeniblo")

    println(game1)

    game1.let {
        it.dataNascimento = "18/09/2000"
        it.usuario = "jaque.skywalker"
    }.also {
        println(game1.idInterno)
    }
    println(game1)
    println(game2)

    game1.usuario = "lalala"
    println(game1)
}