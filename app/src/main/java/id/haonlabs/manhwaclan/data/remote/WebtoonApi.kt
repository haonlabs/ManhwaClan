package id.haonlabs.manhwaclan.data.remote

import id.haonlabs.manhwaclan.data.response.WebtoonContentResponse
import id.haonlabs.manhwaclan.data.response.WebtoonDetailResponse
import id.haonlabs.manhwaclan.data.response.WebtoonResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface WebtoonApi {
    @GET("popular")
    suspend fun getPopular(): WebtoonResponse

    @GET("detail/{url}")
    suspend fun getDetail(
        @Path("url") url: String,
    ): WebtoonDetailResponse

    @GET("baca/{url}")
    suspend fun getContentImage(
        @Path("url") url: String,
    ): WebtoonContentResponse
}
