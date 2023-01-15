package tech.takahana.iconwallpaper.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import tech.takahana.iconwallpaper.shared.coroutines.flow.MutableSharedFlowReplayable
import tech.takahana.iconwallpaper.usecase.home.HomeConfirmUseCase
import tech.takahana.iconwallpaper.usecase.home.ImageAssetUseCaseModel
import tech.takahana.iconwallpaper.usecase.home.SelectedBackgroundColorUseCaseModel
import tech.takahana.iconwallpaper.usecase.home.SelectedPatternUseCaseModel
import tech.takahana.iconwallpaper.usecase.home.SetWallpaperTargetUseCaseModel

class FakeHomeConfirmUseCase : HomeConfirmUseCase {

    var saveWallpaperImpl: () -> Result<Unit> = { Result.success(Unit) }

    var setWallpaperImpl: () -> Result<Unit> = { Result.success(Unit) }

    var cancelSetWallpaperImpl: () -> Result<Unit> = { Result.success(Unit) }

    var recycleWallpaperImpl: () -> Unit = {}

    var selectSetWallpaperTargetImpl: (SetWallpaperTargetUseCaseModel) -> Result<SetWallpaperTargetUseCaseModel> =
        {
            Result.success(it)
        }

    var selectedImageAssetFlowImpl: MutableSharedFlow<ImageAssetUseCaseModel> =
        MutableSharedFlowReplayable()

    var selectedPatternFlowImpl: MutableSharedFlow<SelectedPatternUseCaseModel> =
        MutableSharedFlowReplayable()

    var selectedBackgroundColorFlowImpl: MutableSharedFlow<SelectedBackgroundColorUseCaseModel> =
        MutableSharedFlowReplayable()

    override val selectedImageAssetFlow: Flow<ImageAssetUseCaseModel> =
        selectedImageAssetFlowImpl.asSharedFlow()

    override val selectedPatternFlow: Flow<SelectedPatternUseCaseModel> =
        selectedPatternFlowImpl.asSharedFlow()

    override val selectedBackgroundColorFlow: Flow<SelectedBackgroundColorUseCaseModel> =
        selectedBackgroundColorFlowImpl.asSharedFlow()

    override fun saveWallpaper(): Result<Unit> = saveWallpaperImpl()

    override fun setWallpaper(): Result<Unit> = setWallpaperImpl()

    override fun cancelSetWallpaper(): Result<Unit> = cancelSetWallpaperImpl()

    override fun selectSetWallpaperTarget(target: SetWallpaperTargetUseCaseModel): Result<SetWallpaperTargetUseCaseModel> =
        selectSetWallpaperTargetImpl(target)

    override fun recycleWallpaper(): Unit = recycleWallpaperImpl()
}
