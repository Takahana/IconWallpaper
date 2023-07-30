package tech.takahana.iconwallpaper.android.home.ui.screen

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
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
                title = { },
                navigationIcon = {
                    val navBackStackEntry by homeNavHostController.currentBackStackEntryAsState()
                    if (navBackStackEntry?.destination?.route != Screen.HomeSelectImageAssetContent.route) {
                        IconButton(
                            onClick = { homeNavHostController.popBackStack() }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.ArrowBack,
                                contentDescription = stringResource(id = R.string.back),
                            )
                        }
                    }
                },
                backgroundColor = Color.White,
                elevation = 0.dp,
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
