package id.haonlabs.manhwaclan.data.response

import com.google.gson.annotations.SerializedName

data class WebtoonDetailResponse(
    @field:SerializedName("data")
    val data: Data? = null,
    @field:SerializedName("success")
    val success: String? = null,
    @field:SerializedName("message")
    val message: String? = null,
)

data class Data(
    @field:SerializedName("judul_alternatif")
    val judulAlternatif: String? = null,
    @field:SerializedName("chapter")
    val chapter: List<ChapterItem?>? = null,
    @field:SerializedName("img")
    val img: String? = null,
    @field:SerializedName("short_sinopsis")
    val shortSinopsis: String? = null,
    @field:SerializedName("jenis_komik")
    val jenisKomik: String? = null,
    @field:SerializedName("official")
    val official: List<OfficialItem?>? = null,
    @field:SerializedName("title")
    val title: String? = null,
    @field:SerializedName("mirip")
    val mirip: List<MiripItem?>? = null,
    @field:SerializedName("grafis")
    val grafis: String? = null,
    @field:SerializedName("ilustrator")
    val ilustrator: String? = null,
    @field:SerializedName("tema")
    val tema: List<String?>? = null,
    @field:SerializedName("ratting")
    val ratting: String? = null,
    @field:SerializedName("spoiler")
    val spoiler: List<String?>? = null,
    @field:SerializedName("status")
    val status: String? = null,
    @field:SerializedName("pengarang")
    val pengarang: String? = null,
    @field:SerializedName("informasi")
    val informasi: List<InformasiItem?>? = null,
)

data class InformasiItem(
    @field:SerializedName("img")
    val img: String? = null,
    @field:SerializedName("title")
    val title: String? = null,
)

data class ChapterItem(
    @field:SerializedName("chapter")
    val chapter: String? = null,
    @field:SerializedName("update")
    val update: String? = null,
    @field:SerializedName("url")
    val url: String? = null,
)

data class OfficialItem(
    @field:SerializedName("img")
    val img: String? = null,
    @field:SerializedName("title")
    val title: String? = null,
)

data class MiripItem(
    @field:SerializedName("img")
    val img: String? = null,
    @field:SerializedName("subtitle")
    val subtitle: String? = null,
    @field:SerializedName("jenis")
    val jenis: String? = null,
    @field:SerializedName("title")
    val title: String? = null,
    @field:SerializedName("type")
    val type: String? = null,
    @field:SerializedName("url")
    val url: String? = null,
)
