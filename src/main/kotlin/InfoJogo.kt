import com.google.gson.annotations.SerializedName

class InfoJogo(
    @SerializedName("info") val info: Jogo
) {
    override fun toString(): String {
        return info.toString()
    }
}