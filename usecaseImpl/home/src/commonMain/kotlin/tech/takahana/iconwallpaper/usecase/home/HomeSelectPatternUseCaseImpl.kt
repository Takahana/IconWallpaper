package tech.takahana.iconwallpaper.usecase.home

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import tech.takahana.iconwallpaper.repository.asset.SelectBackgroundColorRepository
import tech.takahana.iconwallpaper.repository.asset.SelectImageAssetRepository
import tech.takahana.iconwallpaper.repository.asset.SelectPatternTypeRepository
import tech.takahana.iconwallpaper.shared.domain.domainobject.ColorType
import tech.takahana.iconwallpaper.shared.domain.domainobject.PatternType

class HomeSelectPatternUseCaseImpl(
    private val selectPatternTypeRepository: SelectPatternTypeRepository,
    private val selectBackgroundColorRepository: SelectBackgroundColorRepository,
    selectImageAssetRepository: SelectImageAssetRepository
) : HomeSelectPatternUseCase {

    override val selectedPatternFlow: Flow<SelectedPatternUseCaseModel> =
        selectPatternTypeRepository.selectedPatternTypeFlow.map { patternType ->
            SelectedPatternUseCaseModel(patternType)
        }

    override val selectedBackgroundColorFlow: Flow<SelectedBackgroundColorUseCaseModel> =
        selectBackgroundColorRepository.selectBackgroundColorFlow.map { backgroundColor ->
            SelectedBackgroundColorUseCaseModel(backgroundColor)
        }
    override val selectedImageAssetFlow: Flow<ImageAssetUseCaseModel> =
        selectImageAssetRepository.selectedImageAssetFlow.filterNotNull().map { imageAsset ->
            ImageAssetUseCaseModel.HasAsset(asset = imageAsset, isSelected = true)
        }

    override suspend fun selectPattern(patternType: PatternType) {
        selectPatternTypeRepository.setSelectedPatternType(patternType)
    }

    override suspend fun selectBackgroundColor(backgroundColor: ColorType) {
        selectBackgroundColorRepository.setSelectedBackgroundColor(backgroundColor)
    }

    override suspend fun changeTab(target: SwitchTabUseCaseModel): Result<SwitchTabUseCaseModel> {
        return Result.success(target)
    }
}
