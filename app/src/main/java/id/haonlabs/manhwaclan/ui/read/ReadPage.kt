package id.haonlabs.manhwaclan.ui.read

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.haonlabs.manhwaclan.utils.NetworkImageWithProgress

@Composable
fun ReadPage(
    url: String,
    viewModel: ReadViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val imageUrls = viewModel.content?.img ?: emptyList()

    LaunchedEffect(Unit) {
        viewModel.getContentImage(url)
    }
    LazyColumn(
        modifier =
            modifier
                .fillMaxSize()
                .background(Color.White),
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        itemsIndexed(imageUrls) { index, item ->
            NetworkImageWithProgress(
                index = index,
                imageUrl = item.toString(),
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}
