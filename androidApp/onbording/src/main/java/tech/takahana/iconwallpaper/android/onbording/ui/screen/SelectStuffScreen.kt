package tech.takahana.iconwallpaper.android.onbording.ui.screen

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import tech.takahana.iconwallpaper.android.onbording.ui.components.AppBar
import tech.takahana.iconwallpaper.android.onbording.ui.theme.IconWallPaperTheme

@Composable
fun SelectStuffScreen() {
    IconWallPaperTheme {
        Scaffold(
            topBar = {
                AppBar()
            },
        ) {
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewSelectStuffScreen() {
    SelectStuffScreen()
}