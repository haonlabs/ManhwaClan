package id.haonlabs.manhwaclan.ui.read

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.haonlabs.manhwaclan.data.response.Dataa
import id.haonlabs.manhwaclan.repository.WebtoonRepository
import id.haonlabs.manhwaclan.ui.detail.DetailViewModel.Companion.TAG
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReadViewModel
    @Inject
    constructor(
        private val repository: WebtoonRepository,
    ) : ViewModel() {
        var content by mutableStateOf<Dataa?>(null)

        fun getContentImage(url: String) {
            viewModelScope.launch {
                try {
                    content = repository.fetchContent(url)
                    Log.d(TAG, "getWebtoonContent: $url")
                    Log.d(TAG, "getWebtoonContent: $content")
                } catch (e: Exception) {
                    Log.e(TAG, "webtoonContent: $e")
                }
            }
        }
    }
