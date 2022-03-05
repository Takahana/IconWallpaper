package tech.takahana.iconwallpaper.android.home.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import tech.takahana.iconwallpaper.android.core.ui.theme.IconWallPaperTheme
import tech.takahana.iconwallpaper.android.home.R
import tech.takahana.iconwallpaper.android.home.ui.type.PatternType

@Composable
fun ImagePattern(patternType: PatternType) {
    val image = ImageBitmap.imageResource(
        id = R.drawable.cat
    )
    when (patternType) {
        PatternType.SMALL -> {
            Canvas(Modifier.fillMaxSize()) {
                drawImage(
                    image = image,
                )
            }
        }
        PatternType.MEDIUM -> {
            Canvas(Modifier.fillMaxSize()) {
                val canvasSize = size
                val canvasWidth = size.width
                val canvasHeight = size.height
                drawImage(
                    image = ImageBitmap(
                        width = canvasWidth.toInt() - 2,
                        height = canvasHeight.toInt() - 2
                    ).also {
                        it.readPixels(
                            width = canvasWidth.toInt() - 1,
                            height = canvasHeight.toInt() - 1,
                            buffer = image.toPixelMap().buffer
                        )
                    }
                )
                drawImage(
                    image = image,
                    topLeft = Offset(x = canvasWidth / 3F, y = 0F),
                )
            }
        }
        PatternType.LARGE -> {
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
