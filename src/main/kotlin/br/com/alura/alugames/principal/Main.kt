package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.modelo.Jogo
import br.com.alura.alugames.servicos.ConsumoApi
import java.util.*


fun main() {
    val leitura = Scanner(System.`in`)
    val gamer = Gamer.criarGame(leitura)

    println("Cadastro concluido com sucesso")
    println(gamer)

    do {
        println("Digite o código do jogo para buscar: ")
        val busca = leitura.nextLine()

        var meuJogo: Jogo? = null

        val resultado = runCatching {
            val buscaApi = ConsumoApi()
            val informacaoJogo = buscaApi.buscaJogo(busca)

            meuJogo = Jogo(informacaoJogo.info.title, informacaoJogo.info.thumb)
        }

        resultado.onFailure {
            println("Jogo não existe")
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

            gamer.jogosBuscados.add(meuJogo)
        }

        println("Deseja buscar novo jogo? s/n")
        val resposta = leitura.nextLine()
    } while (resposta.equals("s", true))

    println("Jogos buscados")
    println(gamer.jogosBuscados)
    println("\nJogos ordenados por titulo")
    gamer.jogosBuscados.sortBy {
        it?.titulo
    }
    gamer.jogosBuscados.forEach{
        println("Titulo: " + it?.titulo)
    }

    val jogosFiltrados = gamer.jogosBuscados.filter {
        it?.titulo?.contains("batman", true) ?: false
    }

    println("\nJogos filtrados")
    println(jogosFiltrados)

    println("\nDeseja excluir um item da lista?")
    val opcao = leitura.nextLine()
    if (opcao.equals("s", true)) {
        println("Informe a posição do jogo que deseja excluir")
        println(gamer.jogosBuscados)
        val index = leitura.nextInt()
        gamer.jogosBuscados.removeAt(index)
    }

    println("\nLista atualizada")
    println(gamer.jogosBuscados)

    println("Busca finalizada com sucesso ...")
}