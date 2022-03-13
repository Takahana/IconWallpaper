package tech.takahana.iconwallpaper.android.home.ui.util

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize

object DrawScopeUtils {
    fun DrawScope.drawPattern(
        image: ImageBitmap,
        backGroundColor: Color,
        drawNum: Int
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        if (drawNum == 1) {
            drawImage(
                image = image,
                dstSize = IntSize(canvasWidth.toInt(), canvasHeight.toInt())
            )
            return
        }
        val dstSize = IntSize((canvasWidth / drawNum).toInt(), (canvasHeight / drawNum).toInt())
        val coverSize = Size((canvasWidth / drawNum / 2), (canvasHeight / drawNum))
        var dstOffset: IntOffset
        for (j in 0 until drawNum) {
            for (i in 0 until drawNum) {
                if (j % 2 == 0) {
                    dstOffset = IntOffset(
                        x = (canvasWidth / drawNum * i).toInt(),
                        y = (canvasHeight / drawNum * j).toInt()
                    )
                } else {
                    if (i == 0) {
                        drawImage(
                            image = image,
                            dstSize = dstSize,
                            dstOffset = IntOffset(
                                x = (canvasWidth / drawNum * -1 / 2).toInt(),
                                y = (canvasHeight / drawNum * j).toInt()
                            )
                        )
                    }
                    dstOffset = IntOffset(
                        x = (canvasWidth / drawNum * (i + i + 1) / 2).toInt(),
                        y = (canvasHeight / drawNum * j).toInt()
                    )
                }
                drawImage(
                    image = image,
                    dstSize = dstSize,
                    dstOffset = dstOffset
                )
            }
            if (j % 2 != 0) {
                drawRect(
                    color = backGroundColor,
                    topLeft = Offset(
                        x = canvasWidth / drawNum * -1 / 2,
                        y = canvasHeight / drawNum * j
                    ),
                    size = coverSize
                )
                drawRect(
                    color = backGroundColor,
                    topLeft = Offset(
                        x = canvasWidth / drawNum * drawNum * 2 / 2,
                        y = canvasHeight / drawNum * j
                    ),
                    size = coverSize
                )
            }
        }
    }
}
