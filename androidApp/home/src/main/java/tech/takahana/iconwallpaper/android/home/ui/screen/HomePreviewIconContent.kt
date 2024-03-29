package tech.takahana.iconwallpaper.android.home.ui.screen

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import tech.takahana.iconwallpaper.android.core.Screen
import tech.takahana.iconwallpaper.android.core.ui.components.RoundButton
import tech.takahana.iconwallpaper.android.home.ui.screen.viewmodel.HomeSelectPatternViewModel
import tech.takahana.iconwallpaper.shared.assets.BitmapImageAsset
import tech.takahana.iconwallpaper.uilogic.home.HomeSelectPatternUiLogic
import tech.takahana.iconwallpaper.uilogic.home.ImageAssetUiModel

@Composable
fun HomePreviewIconContent(
    homeNavController: NavHostController,
    viewModel: HomeSelectPatternViewModel = viewModel(),
    uiLogic: HomeSelectPatternUiLogic = viewModel.selectPatternUiLogic
) {
    val imageAssetUiModel by uiLogic.selectedImageAssetStateFlow.collectAsState()

    val imageAsset = when (imageAssetUiModel) {
        is ImageAssetUiModel.AssetSelectable -> {
            (imageAssetUiModel as ImageAssetUiModel.AssetSelectable).imageAsset
        }

        is ImageAssetUiModel.None -> null
    }

    val bitmap: Bitmap? = (imageAsset as? BitmapImageAsset)?.bitmap

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (bitmap != null) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(256.dp)
            )
        } else {
            CircularProgressIndicator()
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "この画像でよろしいですか？")
        Row(
            modifier = Modifier.padding(12.dp), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RoundButton(
                modifier = Modifier.fillMaxWidth(0.5F),
                onClick = { homeNavController.popBackStack() },
                backgroundColor = Color.LightGray,
                text = "いいえ",
            )
            Spacer(modifier = Modifier.width(8.dp))
            RoundButton(
                modifier = Modifier,
                onClick = {
                    if (bitmap != null) {
                        homeNavController.navigate(Screen.HomeSelectPatternContent.route)
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                text = "はい",
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewHomePreviewIconContent() {
    HomePreviewIconContent(homeNavController = rememberNavController())
}
