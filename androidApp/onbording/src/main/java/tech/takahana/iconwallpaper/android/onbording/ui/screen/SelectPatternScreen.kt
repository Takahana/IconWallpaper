package tech.takahana.iconwallpaper.android.onbording.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.Palette
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.takahana.iconwallpaper.android.onbording.ui.components.Announcements
import tech.takahana.iconwallpaper.android.onbording.ui.components.AppBar
import tech.takahana.iconwallpaper.android.onbording.ui.components.BottomButton
import tech.takahana.iconwallpaper.android.onbording.ui.theme.IconWallPaperTheme

@Composable
fun SelectPatternScreen() {
    IconWallPaperTheme {
        Scaffold(
            topBar = {
                AppBar()
            },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Announcements(message = "ステップ2. パターンを選択する")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(384.dp)
                        .background(color = Color.Cyan),
                    contentAlignment = Alignment.Center,
                ) {
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .clickable { /*TODO*/ }
                            .fillMaxWidth(0.5F)
                            .height(52.dp)
                            .background(color = MaterialTheme.colors.secondary),
                        contentAlignment = Alignment.Center,
                    ) {
                        Row {
                            Icon(Icons.Filled.GridOn, contentDescription = null)
                            Text("パターン")
                        }
                    }
                    Box(
                        modifier = Modifier
                            .clickable { /*TODO*/ }
                            .fillMaxWidth()
                            .height(52.dp)
                            .background(color = Color.White),
                        contentAlignment = Alignment.Center,
                    ) {
                        Row {
                            Icon(Icons.Filled.Palette, contentDescription = null)
                            Text("背景色")
                        }

                    }
                }
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Box(
                            modifier = Modifier
                                .size(96.dp)
                                .background(color = Color.Red),
                        ) {}
                        Box(
                            modifier = Modifier
                                .size(96.dp)
                                .background(color = Color.Green),
                        ) {}
                        Box(
                            modifier = Modifier
                                .size(96.dp)
                                .background(color = Color.Blue),
                        ) {}
                    }
                    Spacer(modifier = Modifier.padding(vertical = 16.dp))
                    BottomButton(
                        onClick = { /*TODO*/ },
                        backgroundColor = MaterialTheme.colors.primary,
                        text = "確認画面へ",
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewSelectPatternScreen() {
    SelectPatternScreen()
}
