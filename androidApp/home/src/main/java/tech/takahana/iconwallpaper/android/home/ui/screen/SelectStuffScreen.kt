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
import tech.takahana.iconwallpaper.android.home.R
import tech.takahana.iconwallpaper.android.home.ui.components.Announcements
import tech.takahana.iconwallpaper.android.home.ui.components.ItemGrid
import tech.takahana.iconwallpaper.android.home.ui.components.PrimaryColorButton

@Composable
fun SelectStuffScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Announcements(message = stringResource(R.string.step1_seclect_stuff))
        ItemGrid(columnNum = 5)
        Box(
            modifier = Modifier
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            PrimaryColorButton(
                onClick = { /*TODO*/ },
                backgroundColor = Color.LightGray,
                text = stringResource(R.string.please_select_stuff),
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewSelectStuffScreen() {
    SelectStuffScreen()
}