package tech.takahana.iconwallpaper.android.home.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import tech.takahana.iconwallpaper.android.core.ui.theme.IconWallPaperTheme
import tech.takahana.iconwallpaper.android.home.R
import tech.takahana.iconwallpaper.android.home.ui.type.PatternType

@Composable
fun ImagePattern(patternType: PatternType) {
    val image = ImageBitmap.imageResource(
        id = R.drawable.cat
    )
    Canvas(Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        when (patternType) {
            PatternType.SMALL -> {
                drawImage(
                    image = image,
                    dstSize = IntSize(canvasWidth.toInt(), canvasHeight.toInt())
                )
            }
            PatternType.MEDIUM -> {
                for (i in 0 until 3) {
                    if (i == 0) {
                        drawImage(
                            image = image,
                            dstSize = IntSize(
                                (canvasWidth / 3).toInt(),
                                (canvasHeight / 3).toInt()
                            ),
                            dstOffset = IntOffset(x = 0, y = 0)
                        )
                        continue
                    }
                    drawImage(
                        image = image,
                        dstSize = IntSize((canvasWidth / 3).toInt(), (canvasHeight / 3).toInt()),
                        dstOffset = IntOffset(x = (canvasWidth / 3 * i).toInt(), y = 0)
                    )
                }
            }
            PatternType.LARGE -> {
            }
        }
    }
}

@Preview
@Composable
private fun PreviewImagePattern() {
    IconWallPaperTheme {
        Surface {
            ImagePattern(patternType = PatternType.MEDIUM)
        }
    }
}
