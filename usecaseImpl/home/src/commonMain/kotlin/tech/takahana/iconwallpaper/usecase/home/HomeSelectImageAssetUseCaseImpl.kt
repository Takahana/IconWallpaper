package tech.takahana.iconwallpaper.usecase.home

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tech.takahana.iconwallpaper.repository.asset.LocalImageAssetRepository
import tech.takahana.iconwallpaper.repository.asset.SelectImageAssetRepository
import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset

class HomeSelectImageAssetUseCaseImpl(
    private val localImageAssetRepository: LocalImageAssetRepository,
    private val selectImageAssetRepository: SelectImageAssetRepository
) : HomeSelectImageAssetUseCase {

    override val selectedImageAssetFlow: Flow<SelectedImageAssetUseCaseModel> =
        selectImageAssetRepository.selectedImageAssetFlow.map { imageAsset ->
            SelectedImageAssetUseCaseModel(imageAsset)
        }

    override suspend fun getAllImageAsset(): List<ImageAsset> {
        return localImageAssetRepository.getAll()
    }

    override suspend fun selectImageAsset(imageAsset: ImageAsset) {
        selectImageAssetRepository.setSelectedImageAsset(imageAsset)
    }

    override suspend fun unselectImageAsset() {
        selectImageAssetRepository.clearSelectedImageAsset()
    }
}
