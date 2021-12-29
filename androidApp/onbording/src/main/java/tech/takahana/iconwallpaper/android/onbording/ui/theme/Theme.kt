package tech.takahana.iconwallpaper.android.onbording.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Red50 = Color(0xffffebee)
val Red100 = Color(0xffffcdd2)
val Red800 = Color(0xffd00036)
val Teal50 = Color(0xffe0f2f1)
val Teal100 = Color(0xffb2dfdb)

private val LightColors = lightColors(
    primary = Red50,
    primaryVariant = Red100,
    onPrimary = Color.White,
    secondary = Teal50,
    secondaryVariant = Teal100,
    onSecondary = Color.White,
    error = Red800,
)

@Composable
fun IconWallPaperTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightColors,
        content = content,
    )
}
