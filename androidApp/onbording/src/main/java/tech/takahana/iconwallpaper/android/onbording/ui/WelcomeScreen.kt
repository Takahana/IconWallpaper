package tech.takahana.iconwallpaper.android.onbording.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WelcomeScreen() {
    Text(text = "Wellcome!!")
}

@Preview(showSystemUi = true)
@Composable
fun PreviewWelcomeScreen() {
    WelcomeScreen()
}