package tech.takahana.iconwallpaper.android.home.ui.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import kotlin.math.floor

object DrawScopeUtils {
    fun DrawScope.drawPattern(
        image: ImageBitmap,
        backgroundColor: Color,
        drawNum: Int
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        // 背景を描画。
        drawRect(color = backgroundColor)

        if (drawNum == 1) {
            // dstWidth, dstHeight = 画像のアスペクト比を保った出力画像のサイズ。
            val dstWidth = canvasWidth
            val dstHeight = (image.height / image.width) * dstWidth
            drawImage(
                image = image,
                dstSize = IntSize(dstWidth.toInt(), dstHeight.toInt()),
            )
            return
        }

        // dstWidth, dstHeight = 画像のアスペクト比を保った出力画像のサイズ。
        val dstWidth = canvasWidth / drawNum
        val dstHeight = (image.height / image.width) * dstWidth
        // 小数点は切り捨て
        val rowDrawNum = floor(canvasHeight / dstHeight).toInt()
        var dstSize: IntSize
        var dstOffset: IntOffset
        for (rowIndex in 0 until rowDrawNum) {
            for (columnIndex in 0 until drawNum) {
                val isLastColumn = columnIndex == drawNum - 1

                if (rowIndex % 2 == 0) {
                    dstOffset = IntOffset(
                        x = (dstWidth * columnIndex).toInt(),
                        y = (dstHeight * rowIndex).toInt()
                    )
                } else {
                    if (columnIndex == 0) {
                        dstSize = IntSize(dstWidth.toInt(), dstHeight.toInt())
                        drawImage(
                            image = image,
                            // 元画像の右半分だけ描画する。
                            srcOffset = IntOffset(
                                x = image.width / 2,
                                y = 0,
                            ),
                            dstSize = dstSize,
                            dstOffset = IntOffset(
                                x = 0,
                                y = (dstHeight * rowIndex).toInt()
                            ),
                        )
                    }
                    dstOffset = IntOffset(
                        x = (dstWidth * (columnIndex + columnIndex + 1) / 2).toInt(),
                        y = (dstHeight * rowIndex).toInt()
                    )
                }

                val srcSize: IntSize
                if (rowIndex % 2 != 0 && isLastColumn) {
                    // 元画像の左半分だけ描画する。
                    srcSize = IntSize(image.width / 2, image.height)
                    dstSize = IntSize((dstWidth / 2).toInt(), dstHeight.toInt())
                } else {
                    srcSize = IntSize(image.width, image.height)
                    dstSize = IntSize(dstWidth.toInt(), dstHeight.toInt())
                }
                drawImage(
                    image = image,
                    srcSize = srcSize,
                    dstSize = dstSize,
                    dstOffset = dstOffset
                )
            }
        }
    }
}
