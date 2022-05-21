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
import tech.takahana.iconwallpaper.shared.domain.domainobject.BackgroundColor
import tech.takahana.iconwallpaper.uilogic.home.HomeSelectBackgroundColorUiLogic

@Composable
fun SelectBackgroundTab(
    selectBackgroundColorUiLogic: HomeSelectBackgroundColorUiLogic,
    backgroundColor: BackgroundColor
) {
    val onClick = { color: BackgroundColor ->
        selectBackgroundColorUiLogic.onClickedBackgroundColor(color)
    }
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            BackgroundColorCompose(
                color = BackgroundColor.RED, currentColor = backgroundColor, onClick
            )
            Spacer(modifier = Modifier.padding(12.dp))
            BackgroundColorCompose(
                color = BackgroundColor.BLUE, currentColor = backgroundColor, onClick
            )
            Spacer(modifier = Modifier.padding(12.dp))
            BackgroundColorCompose(
                color = BackgroundColor.GREEN, currentColor = backgroundColor, onClick
            )
            Spacer(modifier = Modifier.padding(12.dp))
            BackgroundColorCompose(
                color = BackgroundColor.PURPLE, currentColor = backgroundColor, onClick
            )
            Spacer(modifier = Modifier.padding(12.dp))
            BackgroundColorCompose(
                color = BackgroundColor.YELLOW, currentColor = backgroundColor, onClick
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            BackgroundColorCompose(
                color = BackgroundColor.BEIGE, currentColor = backgroundColor, onClick
            )
            Spacer(modifier = Modifier.padding(12.dp))
            BackgroundColorCompose(
                color = BackgroundColor.CYAN, currentColor = backgroundColor, onClick
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

@Composable
fun BackgroundColorCompose(
    color: BackgroundColor,
    currentColor: BackgroundColor,
    onClick: (BackgroundColor) -> Unit
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .clickable { onClick(color) }
            .size(40.dp)
            .background(Color(color.rgb))
    ) {
        if (color == currentColor) {
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
//    SelectBackgroundTab()
}
