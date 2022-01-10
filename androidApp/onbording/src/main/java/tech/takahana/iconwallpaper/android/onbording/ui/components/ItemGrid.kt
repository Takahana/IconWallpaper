package tech.takahana.iconwallpaper.android.onbording.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ItemGrid(columnNum: Int) {
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
        for (i in 1..columnNum) {
            item { Spacer(modifier = Modifier.padding(vertical = 12.dp)) }
            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Spacer(modifier = Modifier.padding(horizontal = 12.dp))
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RectangleShape)
                            .background(Color.Blue)
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 12.dp))
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(Color.Green)
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 12.dp))
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.Red)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewItemGrid() {
    ItemGrid(5)
}
