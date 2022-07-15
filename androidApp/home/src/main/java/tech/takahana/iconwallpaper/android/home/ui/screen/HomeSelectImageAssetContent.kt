package tech.takahana.iconwallpaper.android.home.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush.Companion.verticalGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tech.takahana.iconwallpaper.android.core.Screen
import tech.takahana.iconwallpaper.android.core.ui.components.RoundButton
import tech.takahana.iconwallpaper.android.core.ui.theme.Gray100
import tech.takahana.iconwallpaper.android.core.ui.theme.IconWallPaperTheme
import tech.takahana.iconwallpaper.android.home.R
import tech.takahana.iconwallpaper.android.home.ui.components.ImageAssetItemGrid
import tech.takahana.iconwallpaper.android.home.ui.components.StepAnnouncement
import tech.takahana.iconwallpaper.android.home.ui.screen.viewmodel.HomeSelectImageAssetViewModel
import tech.takahana.iconwallpaper.uilogic.home.HomeSelectImageAssetUiLogic

@Composable
fun HomeSelectImageAssetContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeSelectImageAssetViewModel = viewModel(),
    uiLogic: HomeSelectImageAssetUiLogic = viewModel.uiLogic
) {
    val imageAssetList by uiLogic.imageAssetListStateFlow.collectAsState()
    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {
        val (
            stepAnnouncement,
            itemGrid,
            gradient,
            button
        ) = createRefs()

        StepAnnouncement(
            modifier = Modifier.constrainAs(stepAnnouncement) {
                top.linkTo(parent.top)
            },
            message = stringResource(R.string.home_step1_seclect_image_assets)
        )
        ImageAssetItemGrid(
            modifier = Modifier.constrainAs(itemGrid) {
                top.linkTo(stepAnnouncement.bottom)
                bottom.linkTo(button.top)
                height = Dimension.fillToConstraints
            },
            items = imageAssetList,
            onClickItem = { imageAsset -> uiLogic.onClickedImageAsset(imageAsset) }
        )
        Box(
            modifier = Modifier
                .constrainAs(gradient) {
                    bottom.linkTo(button.top)
                }
                .background(
                    verticalGradient(
                        0.0f to Gray100.copy(alpha = 0f),
                        0.2f to Gray100.copy(alpha = 0.5f),
                        1f to Gray100
                    )
                )
                .fillMaxWidth()
                .height(48.dp)
        )
        Box(
            modifier = Modifier
                .constrainAs(button) {
                    bottom.linkTo(parent.bottom)
                }
                .background(Gray100)
                .padding(horizontal = 16.dp, vertical = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            RoundButton(
                onClick = { navController.navigate(Screen.HomeSelectPatternContent.route) },
                backgroundColor = Color.LightGray,
                text = stringResource(R.string.home_please_select_image_assets),
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewSelectStuffScreen() {
    IconWallPaperTheme {
        Surface {
            HomeSelectImageAssetContent(navController = rememberNavController())
        }
    }
}
