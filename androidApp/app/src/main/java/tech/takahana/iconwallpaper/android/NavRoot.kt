package tech.takahana.iconwallpaper.android

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import tech.takahana.iconwallpaper.android.core.Screen
import tech.takahana.iconwallpaper.android.home.ui.screen.HomeConfirmContent
import tech.takahana.iconwallpaper.android.home.ui.screen.HomeScreen
import tech.takahana.iconwallpaper.android.home.ui.screen.HomeSelectImageAssetContent
import tech.takahana.iconwallpaper.android.home.ui.screen.HomeSelectPatternContent
import tech.takahana.iconwallpaper.android.home.ui.screen.viewmodel.HomeConfirmViewModel
import tech.takahana.iconwallpaper.android.home.ui.screen.viewmodel.HomeSelectImageAssetViewModel
import tech.takahana.iconwallpaper.android.home.ui.screen.viewmodel.HomeSelectPatternViewModel
import tech.takahana.iconwallpaper.android.onbording.ui.screen.WelcomeScreen

@Composable
fun NavRoot() {
    val navController = rememberNavController()
    val homeSelectImageAssetViewModel = hiltViewModel<HomeSelectImageAssetViewModel>()
    val homeSelectPatternViewModel = hiltViewModel<HomeSelectPatternViewModel>()
    val homeConfirmViewModel = hiltViewModel<HomeConfirmViewModel>()
    NavHost(navController = navController, startDestination = Screen.WelcomeScreen.route) {
        composable(Screen.WelcomeScreen.route) { WelcomeScreen(navController = navController) }
        navigation(
            startDestination = Screen.HomeSelectImageAssetContent.route,
            route = Screen.HomeScreen.route
        ) {
            composable(Screen.HomeSelectImageAssetContent.route) {
                HomeScreen(
                    content = {
                        HomeSelectImageAssetContent(
                            navController = navController,
                            viewModel = homeSelectImageAssetViewModel
                        )
                    }
                )
            }
            composable(Screen.HomeSelectPatternContent.route) {
                HomeScreen(
                    content = {
                        HomeSelectPatternContent(
                            navController = navController,
                            viewModel = homeSelectPatternViewModel
                        )
                    }
                )
            }
            composable(Screen.HomeConfirmContent.route) {
                HomeScreen(content = { HomeConfirmContent(viewModel = homeConfirmViewModel) })
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewNavRoot() {
    NavRoot()
}
