package tech.takahana.iconwallpaper.android.home.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import tech.takahana.iconwallpaper.android.core.ui.theme.IconWallPaperTheme
import tech.takahana.iconwallpaper.android.core.utils.color.toComposeColor
import tech.takahana.iconwallpaper.android.home.ui.util.DrawScopeUtils.drawPattern
import tech.takahana.iconwallpaper.shared.assets.BitmapImageAsset
import tech.takahana.iconwallpaper.shared.assets.LocalImageAsset
import tech.takahana.iconwallpaper.shared.domain.domainobject.ColorType
import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset
import tech.takahana.iconwallpaper.shared.domain.domainobject.PatternType
import tech.takahana.iconwallpaper.shared.domain.domainobject.dummy.DummyImageAsset

@Composable
fun ImagePattern(
    patternType: PatternType,
    imageAsset: ImageAsset,
    backgroundColorType: ColorType,
) {
    when (imageAsset) {
        is BitmapImageAsset -> {
            val image = imageAsset.bitmap.asImageBitmap()
            Canvas(Modifier.fillMaxSize()) {
                drawPattern(
                    image = image,
                    backgroundColor = backgroundColorType.toComposeColor(),
                    drawNum = patternType.drawNum
                )
            }
        }
        is LocalImageAsset -> {
            val image = ImageBitmap.imageResource(
                id = imageAsset.resId
            )
            Canvas(Modifier.fillMaxSize()) {
                drawPattern(
                    image = image,
                    backgroundColor = backgroundColorType.toComposeColor(),
                    drawNum = patternType.drawNum
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
            ImagePattern(patternType = PatternType.LARGE, DummyImageAsset(), ColorType.Purple)
        }
    }
}
