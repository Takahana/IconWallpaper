package tech.takahana.iconwallpaper.android.home.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import tech.takahana.iconwallpaper.android.core.ui.theme.IconWallPaperTheme
import tech.takahana.iconwallpaper.android.home.R
import tech.takahana.iconwallpaper.shared.assets.LocalImageAsset
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetName
import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset

@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalCoilApi::class
)
@Composable
fun ImageAssetItemGrid(
    modifier: Modifier = Modifier,
    items: List<ImageAsset>
) {
    LazyVerticalGrid(
        modifier = modifier,
        cells = GridCells.Adaptive(minSize = 120.dp),
        contentPadding = PaddingValues(bottom = 48.dp)
    ) {
        items(items) { item ->
            when (item) {
                is LocalImageAsset -> {
                    Image(
                        modifier = Modifier.fillMaxSize().aspectRatio(1f),
                        painter = painterResource(item.resId),
                        contentDescription = item.name.value
                    )
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
                items = (1..10).map {
                    LocalImageAsset(
                        name = AssetName("cat"),
                        resId = R.drawable.cat
                    )
                }
            )
        }
    }
}
