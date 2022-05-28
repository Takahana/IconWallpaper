package tech.takahana.iconwallpaper.usecase.home

class HomeConfirmUseCaseImpl : HomeConfirmUseCase {
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
}
