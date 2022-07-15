package tech.takahana.iconwallpaper.android.core

sealed class Screen(val route: String) {
    object WelcomeScreen : Screen("welcomeScreen")
    object HomeScreen : Screen("homeScreen")
    object HomeSelectImageAssetContent : Screen("homeSelectImageAssetContent")
    object HomeSelectPatternContent : Screen("homeSelectPatternContent")
    object HomeConfirmContent : Screen("homeConfirmContent")
}
