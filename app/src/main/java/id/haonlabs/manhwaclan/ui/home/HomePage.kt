package id.haonlabs.manhwaclan.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import id.haonlabs.manhwaclan.components.MyTopBar

@Composable
fun HomePage(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    var page by remember { mutableStateOf(0) }
    LaunchedEffect(selectedTabIndex) {
        when (selectedTabIndex) {
            0 -> viewModel.loadLatest(page)
            1 -> viewModel.loadByType("manga", page)
            2 -> viewModel.loadByType("manhwa", page)
            3 -> viewModel.loadByType("manhua", page)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        MyTopBar()
        ComicTabs(selectedTabIndex) { selectedTabIndex = it }

        LazyColumn {
            items(viewModel.webtoons) { webtoon ->
                ComicItem(
                    title = webtoon.title.toString(),
                    latestChapter = webtoon.chapter,
                    rating = webtoon.ratting,
                    image = webtoon.img.toString(),
                    navController = navController,
                    url = webtoon.url.toString(),
                )
            }
        }
    }
}

@Composable
fun ComicTabs(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
) {
    val tabs = listOf("All", "Manhwa", "Manga", "Manhua")

    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        edgePadding = 0.dp,
        indicator = {},
        divider = {},
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) },
                selectedContentColor = Color(0xFF92A968), // green from image
                unselectedContentColor = Color.Black,
            ) {
                Box(
                    modifier =
                        Modifier
                            .padding(8.dp)
                            .background(
                                if (selectedTabIndex == index) Color(0xFF92A968) else Color.Transparent,
                                shape = RoundedCornerShape(8.dp),
                            ).padding(horizontal = 16.dp, vertical = 6.dp),
                ) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        color = if (selectedTabIndex == index) Color.White else Color.Black,
                    )
                }
            }
        }
    }
}

@Composable
fun ComicItem(
    title: String,
    latestChapter: String?,
    rating: String?,
    image: String,
    navController: NavController,
    url: String,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
        onClick = { navController.navigate("detail/${(url).replace("/", "")}") },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
        ) {
            Row {
                AsyncImage(
                    model = image,
                    modifier =
                        Modifier
                            .clip(RoundedCornerShape(8.dp)),
                    contentDescription = "Image",
                )
                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier =
                        Modifier
                            .weight(1f),
                ) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    if (!latestChapter.isNullOrEmpty()) {
                        Text(text = latestChapter)
                    }
                }
            }

            if (!rating.isNullOrEmpty()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier =
                        Modifier
                            .align(Alignment.BottomEnd),
                ) {
                    Icon(Icons.Default.Star, contentDescription = "Rating")
                    Text(text = rating, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
