package tech.takahana.iconwallpaper.android.onbording.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import tech.takahana.iconwallpaper.android.onbording.R

@Composable
fun ItemGrid(items: List<Painter>) {
    LazyColumn {
        item {
            Row(
                modifier = Modifier.scale(0.5F),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(painter = items[0], contentDescription = null)
                Image(painter = items[1], contentDescription = null)
                Image(painter = items[2], contentDescription = null)
            }
        }
        item {
            Row(
                modifier = Modifier.scale(0.5F),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(painter = items[3], contentDescription = null)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewItemGrid() {
    ItemGrid(
        listOf(
            painterResource(id = R.drawable.stuff_1),
            painterResource(id = R.drawable.stuff_2),
            painterResource(id = R.drawable.stuff_3),
            painterResource(id = R.drawable.stuff_4)
        )
    )
}
