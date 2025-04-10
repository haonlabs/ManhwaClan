package id.haonlabs.manhwaclan.repository

import id.haonlabs.manhwaclan.data.remote.WebtoonApi
import id.haonlabs.manhwaclan.data.response.Data
import id.haonlabs.manhwaclan.data.response.DataItem
import id.haonlabs.manhwaclan.data.response.Dataa
import javax.inject.Inject

class WebtoonRepository
    @Inject
    constructor(
        private val api: WebtoonApi,
    ) {
//        suspend fun fetchPopular(): List<DataItem?>? = api.getPopular().data

        suspend fun fetchLatest(page: Int): List<DataItem?>? = api.getLatest(page).data?.data

        suspend fun fetchByType(
            type: String,
            page: Int,
        ): List<DataItem?>? = api.getByType(type, page).data?.data

        suspend fun fetchDetailWebtoon(url: String): Data? = api.getDetail(url).data

        suspend fun fetchContent(url: String): Dataa? = api.getContentImage(url).data
    }
