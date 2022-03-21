package tech.takahana.iconwallpaper.android.home.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import tech.takahana.iconwallpaper.android.core.ui.theme.IconWallPaperTheme
import tech.takahana.iconwallpaper.android.home.R
import tech.takahana.iconwallpaper.android.home.ui.util.DrawScopeUtils.drawPattern
import tech.takahana.iconwallpaper.shared.domain.domainobject.PatternType

@Composable
fun ImagePattern(patternType: PatternType) {
    val image = ImageBitmap.imageResource(
        id = R.drawable.cat
    )
    val backGroundColor = MaterialTheme.colors.background
    Canvas(Modifier.fillMaxSize()) {

        when (patternType) {
            PatternType.SMALL -> {
                drawPattern(
                    image = image,
                    backGroundColor = backGroundColor,
                    drawNum = 1
                )
            }
            PatternType.MEDIUM -> {
                drawPattern(
                    image = image,
                    backGroundColor = backGroundColor,
                    drawNum = 3
                )
            }
            PatternType.LARGE -> {
                drawPattern(
                    image = image,
                    backGroundColor = backGroundColor,
                    drawNum = 5
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewImagePattern() {
    IconWallPaperTheme {
        Surface {
            ImagePattern(patternType = PatternType.LARGE)
        }
    }
}
