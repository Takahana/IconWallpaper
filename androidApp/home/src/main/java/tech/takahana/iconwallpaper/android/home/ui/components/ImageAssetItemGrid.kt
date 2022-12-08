package tech.takahana.iconwallpaper.android.home.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import tech.takahana.iconwallpaper.android.core.ui.theme.IconWallPaperTheme
import tech.takahana.iconwallpaper.android.core.ui.theme.LightBlue50
import tech.takahana.iconwallpaper.android.home.R
import tech.takahana.iconwallpaper.shared.assets.LocalImageAsset
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetId
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetName
import tech.takahana.iconwallpaper.uilogic.home.ImageAssetUiModel

@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalCoilApi::class
)
@Composable
fun ImageAssetItemGrid(
    modifier: Modifier = Modifier,
    items: List<ImageAssetUiModel.Selectable>,
    onClickItem: (ImageAssetUiModel.Selectable) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        cells = GridCells.Adaptive(minSize = 120.dp),
        contentPadding = PaddingValues(bottom = 48.dp)
    ) {
        items(items) { item ->
            when (val imageAsset = item.imageAsset) {
                is LocalImageAsset -> {
                    Box(
                        modifier = Modifier.clickable { onClickItem(item) }
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxSize()
                                .aspectRatio(1f)
                                .background(color = if (item.isSelected) LightBlue50 else Color.Unspecified),
                            painter = painterResource(imageAsset.resId),
                            contentDescription = imageAsset.name.value
                        )
                        if (item.isSelected) {
                            Image(
                                painter = painterResource(R.drawable.ic_check_circle_24),
                                contentDescription = null,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewItemGrid() {
    IconWallPaperTheme {
        Surface {
            ImageAssetItemGrid(
                items = (1..10).map { num ->
                    ImageAssetUiModel.Selectable(
                        imageAsset = LocalImageAsset(
                            id = AssetId.requireGet("cat_$num"),
                            name = AssetName("cat"),
                            resId = R.drawable.cat
                        ),
                        isSelected = num.mod(2) == 0
                    )
                },
                onClickItem = {}
            )
        }
    }
}
