package tech.takahana.iconwallpaper.android.home.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import tech.takahana.iconwallpaper.android.core.ui.theme.IconWallPaperTheme
import tech.takahana.iconwallpaper.android.home.R
import tech.takahana.iconwallpaper.android.home.ui.type.PatternType

@Composable
fun ImagePattern(patternType: PatternType) {
    when (patternType) {
        PatternType.SMALL -> {
            Image(
                painter = painterResource(id = R.drawable.cat),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
            )
        }
        PatternType.MEDIUM -> {

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
            ImagePattern(patternType = PatternType.SMALL)
        }
    }
}
