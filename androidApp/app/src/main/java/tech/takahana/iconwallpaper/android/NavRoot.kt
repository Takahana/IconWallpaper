package tech.takahana.iconwallpaper.android

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tech.takahana.iconwallpaper.android.core.Screen
import tech.takahana.iconwallpaper.android.home.ui.screen.HomeScreen
import tech.takahana.iconwallpaper.android.onbording.ui.screen.WelcomeScreen

@Composable
fun NavRoot() {
    val rootNavController = rememberNavController()
    NavHost(navController = rootNavController, startDestination = Screen.WelcomeScreen.route) {
        composable(Screen.WelcomeScreen.route) { WelcomeScreen(rootNavController = rootNavController) }
        composable(Screen.HomeScreen.route) { HomeScreen(rootNavController = rootNavController) }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewNavRoot() {
    NavRoot()
}
