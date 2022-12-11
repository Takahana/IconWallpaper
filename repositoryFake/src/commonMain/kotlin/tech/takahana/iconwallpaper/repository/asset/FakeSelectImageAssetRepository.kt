package tech.takahana.iconwallpaper.repository.asset

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import tech.takahana.iconwallpaper.shared.assets.BitmapImageAsset
import tech.takahana.iconwallpaper.shared.assets.LocalImageAsset
import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset

class FakeSelectImageAssetRepository : SelectImageAssetRepository {

    var selectedImageAssetFlowImpl: MutableStateFlow<ImageAsset?> = MutableStateFlow(null)

    override val selectedImageAssetFlow: Flow<ImageAsset?> =
        selectedImageAssetFlowImpl.asStateFlow()

    override suspend fun setSelectedImageAsset(imageAsset: ImageAsset) {
        selectedImageAssetFlowImpl.value = imageAsset
    }

    override suspend fun clearSelectedImageAsset() {
        selectedImageAssetFlowImpl.value = null
    }

    override suspend fun recycleImageAsset() {
        when (selectedImageAssetFlowImpl.value) {
            is BitmapImageAsset -> {
                (selectedImageAssetFlowImpl.value as BitmapImageAsset).recycle()
            }
            is LocalImageAsset -> {
                // リサイクル処理が必要な場合は書く
            }
        }
    }
}
