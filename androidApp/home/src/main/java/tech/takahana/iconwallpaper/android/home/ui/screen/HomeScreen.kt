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
import tech.takahana.iconwallpaper.android.home.ui.screen.viewmodel.HomeConfirmViewModel
import tech.takahana.iconwallpaper.android.home.ui.screen.viewmodel.HomeSelectImageAssetViewModel
import tech.takahana.iconwallpaper.android.home.ui.screen.viewmodel.HomeSelectPatternViewModel

@Composable
fun HomeScreen(
    rootNavController: NavHostController
) {
    val homeNavHostController = rememberNavController()
    val homeSelectImageAssetViewModel = hiltViewModel<HomeSelectImageAssetViewModel>()
    val homeSelectPatternViewModel = hiltViewModel<HomeSelectPatternViewModel>()
    val homeConfirmViewModel = hiltViewModel<HomeConfirmViewModel>()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.home_page_title)) },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            Icons.Filled.Menu,
                            contentDescription = stringResource(R.string.home_open_drawer)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            Icons.Filled.Notifications,
                            contentDescription = stringResource(R.string.home_notifications)
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            Icons.Filled.Share,
                            contentDescription = stringResource(R.string.home_share)
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = stringResource(R.string.home_search)
                        )
                    }
                }
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
                        viewModel = homeSelectImageAssetViewModel
                    )
                }
                composable(Screen.HomeSelectPatternContent.route) {
                    HomeSelectPatternContent(
                        homeNavController = homeNavHostController,
                        viewModel = homeSelectPatternViewModel
                    )
                }
                composable(Screen.HomeConfirmContent.route) {
                    HomeConfirmContent(
                        rootNavController = rootNavController,
                        viewModel = homeConfirmViewModel
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
