package tech.takahana.iconwallpaper.usecase.home

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import tech.takahana.iconwallpaper.repository.asset.SelectBackgroundColorRepository
import tech.takahana.iconwallpaper.repository.asset.SelectImageAssetRepository
import tech.takahana.iconwallpaper.repository.asset.SelectPatternTypeRepository

class HomeConfirmUseCaseImpl(
    selectPatternTypeRepository: SelectPatternTypeRepository,
    selectBackgroundColorRepository: SelectBackgroundColorRepository,
    private val selectImageAssetRepository: SelectImageAssetRepository
) : HomeConfirmUseCase {

    override val selectedImageAssetFlow: Flow<ImageAssetUseCaseModel> =
        selectImageAssetRepository.selectedImageAssetFlow.filterNotNull().map { imageAsset ->
            ImageAssetUseCaseModel.HasAsset(asset = imageAsset, isSelected = true)
        }

    override val selectedPatternFlow: Flow<SelectedPatternUseCaseModel> =
        selectPatternTypeRepository.selectedPatternTypeFlow.map { patternType ->
            SelectedPatternUseCaseModel(patternType)
        }

    override val selectedBackgroundColorFlow: Flow<SelectedBackgroundColorUseCaseModel> =
        selectBackgroundColorRepository.selectBackgroundColorFlow.map { backgroundColor ->
            SelectedBackgroundColorUseCaseModel(backgroundColor)
        }

    override fun saveWallpaper(): Result<Unit> {
        return Result.success(Unit)
    }

    override fun setWallpaper(): Result<Unit> {
        return Result.success(Unit)
    }

    override fun cancelSetWallpaper(): Result<Unit> {
        return Result.success(Unit)
    }

    override fun selectSetWallpaperTarget(target: SetWallpaperTargetUseCaseModel): Result<SetWallpaperTargetUseCaseModel> {
        return Result.success(target)
    }

    override fun recycleWallpaper() {
        selectImageAssetRepository.recycleImageAsset()
    }
}
