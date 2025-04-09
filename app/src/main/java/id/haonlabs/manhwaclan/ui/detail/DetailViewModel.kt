package id.haonlabs.manhwaclan.ui.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.haonlabs.manhwaclan.data.response.Data
import id.haonlabs.manhwaclan.repository.WebtoonRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
    @Inject
    constructor(
        private val repository: WebtoonRepository,
    ) : ViewModel() {
        companion object {
            const val TAG = "DetailViewModel"
        }

        var detailData by mutableStateOf<Data?>(null)

        fun getWebtoonDetail(url: String) {
            viewModelScope.launch {
                try {
                    detailData = repository.fetchDetailWebtoon(url)
                    Log.d(TAG, "getWebtoonDetail: $detailData")
                } catch (e: Exception) {
                    Log.e(TAG, "webtoonDetail: $e")
                }
            }
        }
    }
