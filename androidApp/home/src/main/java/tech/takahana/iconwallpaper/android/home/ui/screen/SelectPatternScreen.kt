package tech.takahana.iconwallpaper.android.home.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.Palette
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import tech.takahana.iconwallpaper.android.core.ui.components.RoundButton
import tech.takahana.iconwallpaper.android.core.ui.theme.IconWallPaperTheme
import tech.takahana.iconwallpaper.android.home.R
import tech.takahana.iconwallpaper.android.home.ui.components.ImagePattern
import tech.takahana.iconwallpaper.android.home.ui.components.StepAnnouncement
import tech.takahana.iconwallpaper.android.home.ui.screen.viewmodel.HomeSelectPatternScreenViewModel
import tech.takahana.iconwallpaper.uilogic.home.HomeSelectPatternUiLogic
import tech.takahana.iconwallpaper.uilogic.home.HomeSwitchTabUiLogic
import tech.takahana.iconwallpaper.uilogic.home.SwitchTabUiModel

@Composable
fun SelectPatternScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeSelectPatternScreenViewModel = viewModel(),
    selectPatternUiLogic: HomeSelectPatternUiLogic = viewModel.selectPatternUiLogic,
    switchTabUiLogic: HomeSwitchTabUiLogic = viewModel.switchTabUiLogic
) {
    val resId = R.drawable.cat
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val patternType by selectPatternUiLogic.patternTypeStateFlow.collectAsState()
        val tabState by switchTabUiLogic.switchTabStateFlow.collectAsState()
        StepAnnouncement(message = stringResource(R.string.home_step2_seclect_pattern))
        Box(
            modifier = Modifier
                .heightIn(Dp.Unspecified, 360.dp)
                .widthIn(Dp.Unspecified, 360.dp)
                .background(color = MaterialTheme.colors.secondaryVariant),
            contentAlignment = Alignment.Center
        ) {
            ImagePattern(patternType = patternType, resourceId = resId)
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .clickable { switchTabUiLogic.onClickedTab(SwitchTabUiModel.PATTERN) }
                    .fillMaxWidth(0.5F)
                    .height(52.dp)
                    .background(
                        color = if (tabState == SwitchTabUiModel.PATTERN)
                            MaterialTheme.colors.secondary else Color.White
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Row {
                    Icon(
                        Icons.Filled.GridOn,
                        contentDescription = stringResource(R.string.home_grid_on)
                    )
                    Text(stringResource(R.string.home_pattern))
                }
            }
            Box(
                modifier = Modifier
                    .clickable { switchTabUiLogic.onClickedTab(SwitchTabUiModel.BACKGROUNDCOLOR) }
                    .fillMaxWidth()
                    .height(52.dp)
                    .background(
                        color = if (tabState == SwitchTabUiModel.BACKGROUNDCOLOR)
                            MaterialTheme.colors.secondary else Color.White
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Row {
                    Icon(
                        Icons.Filled.Palette,
                        contentDescription = stringResource(R.string.home_palette)
                    )
                    Text(stringResource(R.string.home_background_color))
                }
            }
        }
        Column(
            modifier = Modifier
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            if (tabState == SwitchTabUiModel.PATTERN) SelectPatternTab(
                selectPatternUiLogic,
                resId,
                patternType
            ) else SelectBackgroundTab()
            Spacer(modifier = Modifier.padding(vertical = 16.dp))
            RoundButton(
                onClick = { /*TODO*/ },
                backgroundColor = MaterialTheme.colors.primary,
                text = stringResource(R.string.home_navigation_check_result),
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewSelectPatternScreen() {
    IconWallPaperTheme {
        Surface {
            SelectPatternScreen()
        }
    }
}
