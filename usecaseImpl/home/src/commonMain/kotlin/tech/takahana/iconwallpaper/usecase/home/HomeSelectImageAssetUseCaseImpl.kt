package tech.takahana.iconwallpaper.usecase.home

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import tech.takahana.iconwallpaper.repository.asset.LocalImageAssetRepository
import tech.takahana.iconwallpaper.repository.asset.SelectImageAssetRepository
import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset

class HomeSelectImageAssetUseCaseImpl(
    localImageAssetRepository: LocalImageAssetRepository,
    private val selectImageAssetRepository: SelectImageAssetRepository
) : HomeSelectImageAssetUseCase {

    override val imageAssetListFlow: Flow<List<ImageAssetUseCaseModel>> =
        combine(
            flowOf(localImageAssetRepository.getAll()),
            selectImageAssetRepository.selectedImageAssetFlow
        ) { localImageAssetList, selectedImageAsset ->
            localImageAssetList.map {
                ImageAssetUseCaseModel.HasAsset(
                    asset = it,
                    isSelected = it.id == selectedImageAsset?.id
                )
            }
        }

    override suspend fun selectImageAsset(imageAsset: ImageAsset) {
        selectImageAssetRepository.setSelectedImageAsset(imageAsset)
    }

    override suspend fun unselectImageAsset() {
        selectImageAssetRepository.clearSelectedImageAsset()
    }
}
