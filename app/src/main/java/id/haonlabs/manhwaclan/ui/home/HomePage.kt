package id.haonlabs.manhwaclan.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val webtoons = viewModel.webtoons

    LazyColumn {
        items(webtoons) { item ->
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            val cleanUrl = item.url.toString().replace("/", "")
                            navController.navigate("detail/$cleanUrl")
                        },
            ) {
                AsyncImage(
                    model = item.img,
                    contentDescription = item.title,
                    modifier =
                        Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(8.dp)),
                )
                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(item.title.toString(), fontWeight = FontWeight.Bold)
                    Text(item.chapter.toString())
                    Text(item.update.toString(), fontSize = 12.sp, color = Color.Gray)
                }
            }
        }
    }
}
