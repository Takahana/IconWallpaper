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
import tech.takahana.iconwallpaper.uilogic.home.FakeHomeSelectBackgroundColorUiLogic
import tech.takahana.iconwallpaper.uilogic.home.HomeSelectBackgroundColorUiLogic

@Composable
fun HomeSelectBackgroundTab(
    modifier: Modifier = Modifier,
    selectBackgroundColorUiLogic: HomeSelectBackgroundColorUiLogic,
    backgroundColor: ColorType
) {
    val onClick = { color: ColorType ->
        selectBackgroundColorUiLogic.onClickedBackgroundColor(color)
    }
    val isChecked = { colorType: ColorType ->
        colorType == backgroundColor
    }
    Column {
        Row(
            modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            ColorButton(
                color = ColorType.Red, isChecked = isChecked(ColorType.Red), onClick = onClick
            )
            Spacer(modifier = Modifier.padding(12.dp))
            ColorButton(
                color = ColorType.Blue, isChecked = isChecked(ColorType.Blue), onClick = onClick
            )
            Spacer(modifier = Modifier.padding(12.dp))
            ColorButton(
                color = ColorType.Green, isChecked = isChecked(ColorType.Green), onClick = onClick
            )
            Spacer(modifier = Modifier.padding(12.dp))
            ColorButton(
                color = ColorType.Purple, isChecked = isChecked(ColorType.Purple), onClick = onClick
            )
            Spacer(modifier = Modifier.padding(12.dp))
            ColorButton(
                color = ColorType.Yellow, isChecked = isChecked(ColorType.Yellow), onClick = onClick
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            ColorButton(
                color = ColorType.Beige, isChecked = isChecked(ColorType.Beige), onClick = onClick
            )
            Spacer(modifier = Modifier.padding(12.dp))
            ColorButton(
                color = ColorType.Cyan, isChecked = isChecked(ColorType.Cyan), onClick = onClick
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
    HomeSelectBackgroundTab(
        selectBackgroundColorUiLogic = FakeHomeSelectBackgroundColorUiLogic(),
        backgroundColor = ColorType.Other(0xffb2dfdb)
    )
}
