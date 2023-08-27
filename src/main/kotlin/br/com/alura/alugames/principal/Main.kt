package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Jogo
import br.com.alura.alugames.servicos.ConsumoApi
import java.util.*


fun main() {
    val leitura = Scanner(System.`in`)
    println("Digite o código do jogo para buscar: ")
    val busca = leitura.nextLine()

    var meuJogo: Jogo? = null

    val resultado = runCatching {
        val buscaApi = ConsumoApi()
        val informacaoJogo = buscaApi.buscaJogo(busca)

        meuJogo = Jogo(informacaoJogo.info.title, informacaoJogo.info.thumb)
    }

    resultado.onFailure {
        println("br.com.alura.alugames.modelo.Jogo não existe")
    }

    resultado.onSuccess {
        println("Deseja inserir uma descrição personalizada? s/n")
        val opcao = leitura.nextLine()

        if (opcao.equals("s", true)) {
            println("Insira a descrição personalizada para o jogo:")
            val descricaoPersonalizada = leitura.nextLine()
            meuJogo?.descricao = descricaoPersonalizada
        }
        else {
            meuJogo?.descricao = meuJogo?.titulo
        }

        println(meuJogo)
    }
}