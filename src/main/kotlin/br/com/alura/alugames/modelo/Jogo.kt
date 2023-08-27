package br.com.alura.alugames.modelo

data class Jogo(
    val titulo: String,
    val capa: String
) {
    var descricao: String? = null

    override fun toString(): String {
        return "br.com.alura.alugames.modelo.Jogo: \n" +
                "titulo=$titulo \n" +
                "capa=$capa \n" +
                "descricao=$descricao"
    }
}