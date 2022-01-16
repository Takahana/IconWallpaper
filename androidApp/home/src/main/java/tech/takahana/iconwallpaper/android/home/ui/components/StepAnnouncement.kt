package tech.takahana.iconwallpaper.android.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.takahana.iconwallpaper.android.home.R

@Composable
fun StepAnnouncement(message: String) {
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

@Preview
@Composable
private fun PreviewStepAnnouncement() {
    StepAnnouncement(stringResource(R.string.home_step1_seclect_stuff))
}
