package tech.takahana.iconwallpaper.android.home.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SelectBackgroundTab() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(96.dp)
            .background(Color.Cyan)
    )
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewSelectBackgroundTab() {
    SelectBackgroundTab()
}
