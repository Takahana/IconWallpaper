package tech.takahana.iconwallpaper.android.home.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import tech.takahana.iconwallpaper.android.core.ui.components.RoundButton
import tech.takahana.iconwallpaper.android.core.ui.theme.IconWallPaperTheme
import tech.takahana.iconwallpaper.android.home.R
import tech.takahana.iconwallpaper.android.home.ui.components.ImagePattern
import tech.takahana.iconwallpaper.android.home.ui.components.StepAnnouncement
import tech.takahana.iconwallpaper.android.home.ui.screen.viewmodel.HomeSelectPatternScreenViewModel
import tech.takahana.iconwallpaper.shared.domain.domainobject.PatternType
import tech.takahana.iconwallpaper.uilogic.home.HomeSelectPatternUiLogic

@Composable
fun SelectPatternScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeSelectPatternScreenViewModel = viewModel(),
    uiLogic: HomeSelectPatternUiLogic = viewModel.uiLogic
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val patternType by uiLogic.patternTypeStateFlow.collectAsState()
        StepAnnouncement(message = stringResource(R.string.home_step2_seclect_pattern))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(384.dp)
                .background(color = MaterialTheme.colors.secondaryVariant),
            contentAlignment = Alignment.Center
        ) {
            ImagePattern(patternType = patternType)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .clickable { /*TODO*/ }
                    .fillMaxWidth(0.5F)
                    .height(52.dp)
                    .background(color = MaterialTheme.colors.secondary),
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
                    .clickable { /*TODO*/ }
                    .fillMaxWidth()
                    .height(52.dp)
                    .background(color = Color.White),
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
                .padding(20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Box(
                    modifier = Modifier
                        .size(96.dp)
                        .background(color = MaterialTheme.colors.secondaryVariant)
                        .clickable { uiLogic.onClickedPattern(PatternType.SMALL) },
                ) {
                    ImagePattern(patternType = PatternType.SMALL)
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
                        .clickable { uiLogic.onClickedPattern(PatternType.MEDIUM) },
                ) {
                    ImagePattern(patternType = PatternType.MEDIUM)
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
                        .clickable { uiLogic.onClickedPattern(PatternType.LARGE) },
                ) {
                    ImagePattern(patternType = PatternType.LARGE)
                    if (patternType == PatternType.LARGE) {
                        Image(
                            painter = painterResource(R.drawable.ic_check_circle_24),
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
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
