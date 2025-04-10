package id.haonlabs.manhwaclan.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.haonlabs.manhwaclan.data.response.DataItem
import id.haonlabs.manhwaclan.repository.WebtoonRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val repository: WebtoonRepository,
    ) : ViewModel() {
        companion object {
            const val TAG = "HomeViewModel"
        }

        var webtoons by mutableStateOf<List<DataItem>>(emptyList())

//        private fun loadPopular() {
//            viewModelScope.launch {
//                try {
//                    webtoons = repository.fetchPopular() as List<DataItem>
//                } catch (e: Exception) {
//                }
//            }
//        }

        fun loadLatest(page: Int) {
            viewModelScope.launch {
                webtoons = repository.fetchLatest(page) as List<DataItem>
            }
        }

        fun loadByType(
            type: String,
            page: Int,
        ) {
            viewModelScope.launch {
                webtoons = repository.fetchByType(type, page) as List<DataItem>
            }
        }
    }
