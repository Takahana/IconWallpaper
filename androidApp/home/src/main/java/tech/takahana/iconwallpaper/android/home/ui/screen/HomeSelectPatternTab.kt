package tech.takahana.iconwallpaper.android.home.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.takahana.iconwallpaper.android.core.CoreResDrawable
import tech.takahana.iconwallpaper.android.home.R
import tech.takahana.iconwallpaper.android.home.ui.components.ImagePattern
import tech.takahana.iconwallpaper.shared.domain.domainobject.ColorType
import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset
import tech.takahana.iconwallpaper.shared.domain.domainobject.PatternType
import tech.takahana.iconwallpaper.shared.domain.domainobject.dummy.DummyImageAsset
import tech.takahana.iconwallpaper.uilogic.home.FakeHomeSelectPatternUiLogic
import tech.takahana.iconwallpaper.uilogic.home.HomeSelectPatternUiLogic

@Composable
fun HomeSelectPatternTab(
    modifier: Modifier = Modifier,
    selectPatternUiLogic: HomeSelectPatternUiLogic,
    imageAsset: ImageAsset,
    patternType: PatternType,
    backgroundColor: ColorType
) {
    Row(
        modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
    ) {
        Box(
            modifier = Modifier
                .size(96.dp)
                .clickable { selectPatternUiLogic.onClickedPattern(PatternType.SMALL) },
        ) {
            ImagePattern(
                patternType = PatternType.SMALL,
                imageAsset = imageAsset,
                backgroundColorType = backgroundColor,
            )
            if (patternType == PatternType.SMALL) {
                Image(
                    painter = painterResource(CoreResDrawable.ic_check_circle_24),
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        Box(
            modifier = Modifier
                .size(96.dp)
                .clickable { selectPatternUiLogic.onClickedPattern(PatternType.MEDIUM) },
        ) {
            ImagePattern(
                patternType = PatternType.MEDIUM,
                imageAsset = imageAsset,
                backgroundColorType = backgroundColor,
            )
            if (patternType == PatternType.MEDIUM) {
                Image(
                    painter = painterResource(CoreResDrawable.ic_check_circle_24),
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        Box(
            modifier = Modifier
                .size(96.dp)
                .clickable { selectPatternUiLogic.onClickedPattern(PatternType.LARGE) },
        ) {
            ImagePattern(
                patternType = PatternType.LARGE,
                imageAsset = imageAsset,
                backgroundColorType = backgroundColor,
            )
            if (patternType == PatternType.LARGE) {
                Image(
                    painter = painterResource(CoreResDrawable.ic_check_circle_24),
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSelectPatternTab() {
    HomeSelectPatternTab(
        selectPatternUiLogic = FakeHomeSelectPatternUiLogic(),
        imageAsset = DummyImageAsset(),
        patternType = PatternType.SMALL,
        backgroundColor = ColorType.Other(0xffb2dfdb)
    )
}
