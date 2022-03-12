package tech.takahana.iconwallpaper.android.home.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
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
    val backGroundColor = MaterialTheme.colors.background
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
                val dstSize = IntSize((canvasWidth / 3).toInt(), (canvasHeight / 3).toInt())
                val coverSize = Size((canvasWidth / 3 / 2), (canvasHeight / 3))
                var dstOffset: IntOffset
                for (j in 0 until 3) {
                    for (i in 0 until 3) {
                        if (j % 2 == 0) {
                            dstOffset = IntOffset(
                                x = (canvasWidth / 3 * i).toInt(),
                                y = (canvasHeight / 3 * j).toInt()
                            )
                        } else {
                            if (i == 0) {
                                drawImage(
                                    image = image,
                                    dstSize = dstSize,
                                    dstOffset = IntOffset(
                                        x = (canvasWidth / 3 * -1 / 2).toInt(),
                                        y = (canvasHeight / 3).toInt()
                                    )
                                )
                            }
                            dstOffset = IntOffset(
                                x = (canvasWidth / 3 * (i + i + 1) / 2).toInt(),
                                y = (canvasHeight / 3).toInt()
                            )
                        }
                        drawImage(
                            image = image,
                            dstSize = dstSize,
                            dstOffset = dstOffset
                        )
                        if (i == 2) {
                            drawRect(
                                color = backGroundColor,
                                topLeft = Offset(
                                    x = canvasWidth / 3 * -1 / 2,
                                    y = canvasHeight / 3F
                                ),
                                size = coverSize
                            )
                            drawRect(
                                color = backGroundColor,
                                topLeft = Offset(
                                    x = canvasWidth / 3 * 6 / 2,
                                    y = canvasHeight / 3F
                                ),
                                size = coverSize
                            )
                        }
                    }
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
