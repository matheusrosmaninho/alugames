import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.*


fun main() {
    val leitura = Scanner(System.`in`)
    println("Digite o código do jogo para buscar: ")
    val busca = leitura.nextLine()

    val endereco = "https://www.cheapshark.com/api/1.0/games?id=$busca"
    val client: HttpClient = HttpClient.newHttpClient()

//    try {
//        val request = HttpRequest.newBuilder()
//            .uri(URI.create(endereco))
//            .build()
//
//        val response = client
//            .send(request, BodyHandlers.ofString())
//
//        val json = response.body()
//        println(json)
//
//        val gson = Gson()
//        val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)
//
//        val meuJogo = Jogo(meuInfoJogo.info.title, meuInfoJogo.info.thumb)
//
//        println(meuJogo)
//    } catch (ex: Exception) {
//        println("Jogo não existe ...")
//        println("Jogo não existe ...")
//    }

    var meuJogo: Jogo? = null

    val resultado = runCatching {
        val request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build()

        val response = client
            .send(request, BodyHandlers.ofString())

        val json = response.body()

        val gson = Gson()
        val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)

        meuJogo = Jogo(meuInfoJogo.info.title, meuInfoJogo.info.thumb)
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

        println(meuJogo)
    }
}