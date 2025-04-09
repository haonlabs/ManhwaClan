package id.haonlabs.manhwaclan.data.response

import com.google.gson.annotations.SerializedName

data class WebtoonResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataItem(

	@field:SerializedName("chapter")
	val chapter: String? = null,

	@field:SerializedName("img")
	val img: String? = null,

	@field:SerializedName("ratting")
	val ratting: Any? = null,

	@field:SerializedName("jenis")
	val jenis: String? = null,

	@field:SerializedName("update")
	val update: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)
