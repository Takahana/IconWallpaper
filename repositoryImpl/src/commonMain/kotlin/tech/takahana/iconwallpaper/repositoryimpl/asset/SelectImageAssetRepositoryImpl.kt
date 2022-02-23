package tech.takahana.iconwallpaper.repositoryimpl.asset

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import tech.takahana.iconwallpaper.repository.asset.SelectImageAssetRepository
import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset

class SelectImageAssetRepositoryImpl : SelectImageAssetRepository {

    private val mutableSelectImageAssetSource = MutableStateFlow<ImageAsset?>(null)

    override val selectedImageAssetFlow: Flow<ImageAsset?> =
        mutableSelectImageAssetSource.asStateFlow()

    override suspend fun setSelectedImageAsset(imageAsset: ImageAsset) {
        mutableSelectImageAssetSource.value = imageAsset
    }

    override suspend fun clearSelectedImageAsset() {
        mutableSelectImageAssetSource.value = null
    }
}
