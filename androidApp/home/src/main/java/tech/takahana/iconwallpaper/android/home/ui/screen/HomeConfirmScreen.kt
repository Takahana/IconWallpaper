package tech.takahana.iconwallpaper.android.home.ui.screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import tech.takahana.iconwallpaper.android.core.ui.theme.IconWallPaperTheme
import tech.takahana.iconwallpaper.android.home.R
import tech.takahana.iconwallpaper.android.home.ui.components.StepAnnouncement

@Composable
fun HomeConfirmScreen(
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {
        val (
            stepAnnouncement,
            canvas,
            buttonContainer,
        ) = createRefs()

        StepAnnouncement(
            modifier = Modifier.constrainAs(stepAnnouncement) {
                top.linkTo(parent.top)
            },
            message = stringResource(R.string.home_step3_confirm)
        )

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(canvas) {
                    top.linkTo(stepAnnouncement.bottom)
                    bottom.linkTo(buttonContainer.top)
                    height = Dimension.fillToConstraints
                }
        ) {
            val canvasSize = size
            drawRect(
                color = Color.Gray,
                size = canvasSize
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .constrainAs(buttonContainer) {
                    top.linkTo(canvas.bottom)
                    bottom.linkTo(parent.bottom)
                },
            horizontalArrangement = Arrangement.Center,
        ) {
            ActionButton(
                textResId = R.string.home_confirm_save_image,
                iconResId = R.drawable.ic_save_24,
                onClick = {},
            )
            Spacer(modifier = Modifier.width(40.dp))
            ActionButton(
                textResId = R.string.home_confirm_set_wallpaper,
                iconResId = R.drawable.ic_wallpaper_24,
                onClick = {},
            )
        }
    }
}

@Composable
private fun ActionButton(
    @StringRes textResId: Int,
    @DrawableRes iconResId: Int,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(148.dp),
        shape = RoundedCornerShape(24.dp),
        border = (BorderStroke(1.dp, Color(0xff707070))),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        contentPadding = PaddingValues(24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(iconResId),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(textResId),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview
@Composable
fun PreviewHomeConfirmScreen() {
    IconWallPaperTheme {
        Surface {
            HomeConfirmScreen()
        }
    }
}