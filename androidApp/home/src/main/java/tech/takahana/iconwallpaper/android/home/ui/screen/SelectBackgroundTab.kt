package tech.takahana.iconwallpaper.android.home.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonDefaults.OutlinedBorderSize
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.takahana.iconwallpaper.android.home.R
import tech.takahana.iconwallpaper.android.home.ui.components.ColorButton
import tech.takahana.iconwallpaper.shared.domain.domainobject.ColorType
import tech.takahana.iconwallpaper.uilogic.home.HomeSelectBackgroundColorUiLogic

@Composable
fun SelectBackgroundTab(
    modifier: Modifier = Modifier,
    selectBackgroundColorUiLogic: HomeSelectBackgroundColorUiLogic,
    backgroundColor: ColorType
) {
    val onClick = { color: ColorType ->
        selectBackgroundColorUiLogic.onClickedBackgroundColor(color)
    }
    Column {
        Row(
            modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            ColorButton(
                color = ColorType.Red, currentColor = backgroundColor, onClick
            )
            Spacer(modifier = Modifier.padding(12.dp))
            ColorButton(
                color = ColorType.Blue, currentColor = backgroundColor, onClick
            )
            Spacer(modifier = Modifier.padding(12.dp))
            ColorButton(
                color = ColorType.Green, currentColor = backgroundColor, onClick
            )
            Spacer(modifier = Modifier.padding(12.dp))
            ColorButton(
                color = ColorType.Purple, currentColor = backgroundColor, onClick
            )
            Spacer(modifier = Modifier.padding(12.dp))
            ColorButton(
                color = ColorType.Yellow, currentColor = backgroundColor, onClick
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            ColorButton(
                color = ColorType.Beige, currentColor = backgroundColor, onClick
            )
            Spacer(modifier = Modifier.padding(12.dp))
            ColorButton(
                color = ColorType.Cyan, currentColor = backgroundColor, onClick
            )
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

@Preview
@Composable
private fun PreviewSelectBackgroundTab() {
//    SelectBackgroundTab()
}
