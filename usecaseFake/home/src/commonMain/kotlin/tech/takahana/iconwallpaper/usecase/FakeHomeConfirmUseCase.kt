package tech.takahana.iconwallpaper.usecase

import tech.takahana.iconwallpaper.usecase.home.HomeConfirmUseCase
import tech.takahana.iconwallpaper.usecase.home.SetWallpaperTargetUseCaseModel

class FakeHomeConfirmUseCase : HomeConfirmUseCase {

    var saveWallpaperImpl: () -> Result<Unit> = { Result.success(Unit) }

    var setWallpaperImpl: () -> Result<Unit> = { Result.success(Unit) }

    var cancelSetWallpaperImpl: () -> Result<Unit> = { Result.success(Unit) }

    var selectSetWallpaperTargetImpl: (SetWallpaperTargetUseCaseModel) -> Result<SetWallpaperTargetUseCaseModel> =
        {
            Result.success(it)
        }

    override fun saveWallpaper(): Result<Unit> = saveWallpaperImpl()

    override fun setWallpaper(): Result<Unit> = setWallpaperImpl()

    override fun cancelSetWallpaper(): Result<Unit> = cancelSetWallpaperImpl()

    override fun selectSetWallpaperTarget(target: SetWallpaperTargetUseCaseModel): Result<SetWallpaperTargetUseCaseModel> =
        selectSetWallpaperTargetImpl(target)
}
