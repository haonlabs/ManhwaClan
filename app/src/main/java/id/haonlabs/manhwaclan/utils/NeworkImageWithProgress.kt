package id.haonlabs.manhwaclan.utils

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun NetworkImageWithProgress(
    index: Int = 1,
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    // State to track loading progress
    var loadProgress by remember { mutableStateOf(0f) }

    // Animate the progress value for smoother UI
    val animatedProgress by animateFloatAsState(
        targetValue = loadProgress,
        label = "progress",
    )

    // State to force recomposition on reload
    var reloadKey by remember { mutableStateOf(0) }

    // Track if we're in loading state
    var isLoading by remember { mutableStateOf(false) }

    // Simulate progress updates
    LaunchedEffect(key1 = reloadKey) {
        loadProgress = 0f

        // Simulate progress in smaller increments for smoother experience
        val steps = 20
        val stepDelay = 100L

        for (i in 1..steps) {
            delay(stepDelay)
            loadProgress = (i.toFloat() / steps).coerceAtMost(0.99f)
        }
    }

    SubcomposeAsyncImage(
        model =
            ImageRequest
                .Builder(context)
                .data(imageUrl)
                .setParameter("reload_key", reloadKey)
                .crossfade(true)
                .listener(
                    onStart = {
                        isLoading = true
                    },
                    onSuccess = { _, _ ->
                        isLoading = false
                        loadProgress = 1f
                    },
                    onError = { _, _ ->
                        isLoading = false
                    },
                ).build(),
        contentDescription = "Image $index",
        modifier = modifier,
        contentScale = ContentScale.Crop,
        loading = {
            // This block is executed while the image is loading
            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(0.dp, 60.dp),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(
                    progress = { animatedProgress },
                    modifier = Modifier.size(60.dp),
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 5.dp,
                )

                // Display percentage text inside the progress indicator
                Text(
                    text = "${(animatedProgress * 100).roundToInt()}%",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                )
            }
        },
        error = {
            // This block is executed when the image fails to load
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(0.dp, 60.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(
                    onClick = {
                        // Increment reload key to force recomposition and reload
                        reloadKey++
                        // Reset loading state to trigger progress simulation
                        isLoading = true
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Reload",
                        modifier = Modifier.size(18.dp),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Image: $index")
                }
            }
        },
        success = { state ->
            // This block is executed when the image loads successfully
            SubcomposeAsyncImageContent()
        },
    )
}
