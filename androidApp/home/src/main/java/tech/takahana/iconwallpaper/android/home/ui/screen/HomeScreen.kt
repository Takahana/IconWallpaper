package tech.takahana.iconwallpaper.android.home.ui.screen

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tech.takahana.iconwallpaper.android.core.Screen
import tech.takahana.iconwallpaper.android.home.R

@Composable
fun HomeScreen(
    rootNavController: NavHostController
) {
    val homeNavHostController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
            )
        },
        content = {
            NavHost(
                navController = homeNavHostController,
                startDestination = Screen.HomeSelectImageAssetContent.route
            ) {
                composable(Screen.HomeSelectImageAssetContent.route) {
                    HomeSelectImageAssetContent(
                        homeNavController = homeNavHostController,
                        viewModel = hiltViewModel()
                    )
                }
                composable(Screen.HomeSelectPatternContent.route) {
                    HomeSelectPatternContent(
                        homeNavController = homeNavHostController,
                        viewModel = hiltViewModel()
                    )
                }
                composable(Screen.HomeConfirmContent.route) {
                    HomeConfirmContent(
                        rootNavController = rootNavController,
                        viewModel = hiltViewModel()
                    )
                }
                composable(Screen.HomePreviewIconContent.route) {
                    HomePreviewIconContent(
                        homeNavController = homeNavHostController,
                        viewModel = hiltViewModel()
                    )
                }
            }
        }
    )
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(rootNavController = rememberNavController())
}
