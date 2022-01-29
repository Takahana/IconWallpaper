package tech.takahana.iconwallpaper.android.home.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.takahana.iconwallpaper.android.core.ui.components.RoundButton
import tech.takahana.iconwallpaper.android.home.R
import tech.takahana.iconwallpaper.android.home.ui.components.ItemGrid
import tech.takahana.iconwallpaper.android.home.ui.components.StepAnnouncement

@Composable
fun SelectStuffScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        StepAnnouncement(message = stringResource(R.string.home_step1_seclect_stuff))
        ItemGrid(columnNum = 5)
        Box(
            modifier = Modifier
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            RoundButton(
                onClick = { /*TODO*/ },
                backgroundColor = Color.LightGray,
                text = stringResource(R.string.home_please_select_stuff),
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewSelectStuffScreen() {
    SelectStuffScreen()
}
