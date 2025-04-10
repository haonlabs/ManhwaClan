package id.haonlabs.manhwaclan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import id.haonlabs.manhwaclan.ui.detail.DetailPage
import id.haonlabs.manhwaclan.ui.home.HomePage
import id.haonlabs.manhwaclan.ui.read.ReadPage
import id.haonlabs.manhwaclan.ui.theme.ManhwaClanTheme

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ManhwaClanTheme {
                NavigationRoute()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationRoute() {
    val navController = rememberNavController()
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry.value?.destination?.route

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(route = "home") {
                HomePage(navController)
            }

            // Dynamic route with argument
            composable(
                route = "detail/{url}",
                arguments = listOf(navArgument("url") { type = NavType.StringType }),
            ) { backStackEntry ->
                val url = backStackEntry.arguments?.getString("url") ?: ""
                DetailPage(url = url, navController = navController)
            }

            composable(
                route = "readPage/{url}",
                arguments = listOf(navArgument("url") { type = NavType.StringType }),
            ) { backStackEntry ->
                val url = backStackEntry.arguments?.getString("url") ?: ""
                ReadPage(url = url)
            }
        }
    }
}
