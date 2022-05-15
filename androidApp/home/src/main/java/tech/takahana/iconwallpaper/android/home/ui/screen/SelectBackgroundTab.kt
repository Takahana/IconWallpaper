package tech.takahana.iconwallpaper.android.home.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonDefaults.OutlinedBorderSize
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.takahana.iconwallpaper.android.home.R

@Composable
fun SelectBackgroundTab() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            BackgroundColorCompose(color = Color.Red, isSelect = true)
            Spacer(modifier = Modifier.padding(12.dp))
            BackgroundColorCompose(color = Color.Blue, isSelect = false)
            Spacer(modifier = Modifier.padding(12.dp))
            BackgroundColorCompose(color = Color.Green, isSelect = false)
            Spacer(modifier = Modifier.padding(12.dp))
            BackgroundColorCompose(color = Color(0xFFA260BF), isSelect = false)
            Spacer(modifier = Modifier.padding(12.dp))
            BackgroundColorCompose(color = Color.Yellow, isSelect = false)
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            BackgroundColorCompose(color = Color(0xFFF4BE9B), isSelect = false)
            Spacer(modifier = Modifier.padding(12.dp))
            BackgroundColorCompose(color = Color.Cyan, isSelect = false)
            Spacer(modifier = Modifier.padding(12.dp))
            OutlinedButton(
                modifier = Modifier
                    .height(40.dp)
                    .width(168.dp),
                onClick = { /*TODO*/ },
                border = BorderStroke(OutlinedBorderSize, Color.Black),
                shape = CircleShape,
                colors = ButtonDefaults.textButtonColors(Color.LightGray)
            ) {
                Text(text = stringResource(R.string.others), color = Color.Black)
            }
        }
    }
}

@Composable
fun BackgroundColorCompose(color: Color, isSelect: Boolean) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .clickable { /*TODO*/ }
            .size(40.dp)
            .background(color)
    ) {
        if (isSelect) {
            Image(
                painter = painterResource(R.drawable.ic_check_circle_24),
                contentDescription = null,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewSelectBackgroundTab() {
    SelectBackgroundTab()
}
