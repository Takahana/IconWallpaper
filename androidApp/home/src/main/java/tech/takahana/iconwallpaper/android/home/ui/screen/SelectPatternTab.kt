package tech.takahana.iconwallpaper.android.home.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.takahana.iconwallpaper.android.home.R
import tech.takahana.iconwallpaper.android.home.ui.components.ImagePattern
import tech.takahana.iconwallpaper.shared.domain.domainobject.PatternType
import tech.takahana.iconwallpaper.uilogic.home.HomeSelectPatternUiLogic

@Composable
fun SelectPatternTab(
    selectPatternUiLogic: HomeSelectPatternUiLogic,
    resId: Int,
    patternType: PatternType
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Box(
            modifier = Modifier
                .size(96.dp)
                .background(color = MaterialTheme.colors.secondaryVariant)
                .clickable { selectPatternUiLogic.onClickedPattern(PatternType.SMALL) },
        ) {
            ImagePattern(patternType = PatternType.SMALL, resourceId = resId)
            if (patternType == PatternType.SMALL) {
                Image(
                    painter = painterResource(R.drawable.ic_check_circle_24),
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        Box(
            modifier = Modifier
                .size(96.dp)
                .background(color = MaterialTheme.colors.secondaryVariant)
                .clickable { selectPatternUiLogic.onClickedPattern(PatternType.MEDIUM) },
        ) {
            ImagePattern(patternType = PatternType.MEDIUM, resourceId = resId)
            if (patternType == PatternType.MEDIUM) {
                Image(
                    painter = painterResource(R.drawable.ic_check_circle_24),
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        Box(
            modifier = Modifier
                .size(96.dp)
                .background(color = MaterialTheme.colors.secondaryVariant)
                .clickable { selectPatternUiLogic.onClickedPattern(PatternType.LARGE) },
        ) {
            ImagePattern(patternType = PatternType.LARGE, resourceId = resId)
            if (patternType == PatternType.LARGE) {
                Image(
                    painter = painterResource(R.drawable.ic_check_circle_24),
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewSelectPatternTab() {
//    SelectPatternTab(HomeSelectPatternUiLogic(), R.drawable.cat, PatternType.SMALL)
}
