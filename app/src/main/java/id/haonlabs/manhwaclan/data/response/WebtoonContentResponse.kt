package id.haonlabs.manhwaclan.data.response

import com.google.gson.annotations.SerializedName

data class WebtoonContentResponse(
    @field:SerializedName("data")
    val data: Dataa? = null,
    @field:SerializedName("success")
    val success: Boolean? = null,
    @field:SerializedName("message")
    val message: String? = null,
)

data class Dataa(
    @field:SerializedName("img")
    val img: List<String?>? = null,
    @field:SerializedName("back_chapter")
    val backChapter: String? = null,
    @field:SerializedName("next_chapter")
    val nextChapter: String? = null,
    @field:SerializedName("title")
    val title: String? = null,
)
