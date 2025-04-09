package id.haonlabs.manhwaclan.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import id.haonlabs.manhwaclan.utils.NetworkImageWithProgress

@Composable
fun DetailPage(
    url: String,
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    LaunchedEffect(url) {
        viewModel.getWebtoonDetail(url)
    }

    val data = viewModel.detailData
    val scrollState = rememberScrollState()

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState),
    ) {
        if (data != null) {
            Text(text = data.title ?: "No Title", fontSize = 24.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (data != null) {
            Text(text = "Chapter: ${data.chapter?.last()}")
        }
        Spacer(modifier = Modifier.height(12.dp))

        if (data != null) {
            NetworkImageWithProgress(
                imageUrl = data.img.toString(),
                modifier =
                    Modifier
                        .height(200.dp)
                        .clip(RoundedCornerShape(12.dp)),
            )
        }

        Button(onClick = {
            val cleanUrl =
                data
                    ?.chapter
                    ?.last()
                    ?.url
                    .toString()
                    .replace("/", "")
            navController.navigate("readPage/$cleanUrl")
        }) { Text("Baca") }
    }
}
