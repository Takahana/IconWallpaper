package tech.takahana.iconwallpaper.android.onbording.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Announcements(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Text(message)
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewAnnouncements() {
    Announcements("ステップ1. 素材を選択する")
}
