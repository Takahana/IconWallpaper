package tech.takahana.iconwallpaper.android.onbording.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.takahana.iconwallpaper.android.onbording.R
import tech.takahana.iconwallpaper.android.onbording.ui.components.Announcements
import tech.takahana.iconwallpaper.android.onbording.ui.components.AppBar
import tech.takahana.iconwallpaper.android.onbording.ui.components.BottomButton
import tech.takahana.iconwallpaper.android.onbording.ui.components.ItemGrid
import tech.takahana.iconwallpaper.android.onbording.ui.theme.IconWallPaperTheme

@Composable
fun SelectStuffScreen() {
    IconWallPaperTheme {
        Scaffold(
            topBar = {
                AppBar()
            },
            backgroundColor = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Announcements(message = stringResource(R.string.step1_seclect_stuff))
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                ) {
                    ItemGrid(
                        listOf(
                            painterResource(id = R.drawable.stuff_1),
                            painterResource(id = R.drawable.stuff_2),
                            painterResource(id = R.drawable.stuff_3),
                            painterResource(id = R.drawable.stuff_4)
                        )
                    )
                    BottomButton(
                        onClick = { /*TODO*/ },
                        backgroundColor = Color.LightGray,
                        text = stringResource(R.string.please_select_stuff),
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewSelectStuffScreen() {
    SelectStuffScreen()
}