package tech.takahana.iconwallpaper.android

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import tech.takahana.iconwallpaper.android.home.ui.screen.HomeConfirmScreen
import tech.takahana.iconwallpaper.android.home.ui.screen.HomeScreen
import tech.takahana.iconwallpaper.android.home.ui.screen.HomeSelectImageAssetScreen
import tech.takahana.iconwallpaper.android.home.ui.screen.SelectPatternScreen
import tech.takahana.iconwallpaper.android.home.ui.screen.viewmodel.HomeConfirmViewModel
import tech.takahana.iconwallpaper.android.home.ui.screen.viewmodel.HomeSelectImageAssetViewModel
import tech.takahana.iconwallpaper.android.home.ui.screen.viewmodel.HomeSelectPatternScreenViewModel
import tech.takahana.iconwallpaper.android.onbording.ui.screen.WelcomeScreen

@Composable
fun NavRoot() {
    val navController = rememberNavController()
    val homeSelectImageAssetViewModel = hiltViewModel<HomeSelectImageAssetViewModel>()
    val homeSelectPatternScreenViewModel = hiltViewModel<HomeSelectPatternScreenViewModel>()
    val homeConfirmViewModel = hiltViewModel<HomeConfirmViewModel>()
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController = navController) }
        navigation(startDestination = "selectImage", route = "home") {
            composable("selectImage") {
                HomeScreen(
                    content = {
                        HomeSelectImageAssetScreen(
                            navController = navController,
                            viewModel = homeSelectImageAssetViewModel
                        )
                    }
                )
            }
            composable("selectPattern") {
                HomeScreen(
                    content = {
                        SelectPatternScreen(
                            navController = navController,
                            viewModel = homeSelectPatternScreenViewModel
                        )
                    }
                )
            }
            composable("confirm") {
                HomeScreen(content = { HomeConfirmScreen(viewModel = homeConfirmViewModel) })
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewNavRoot() {
    NavRoot()
}
