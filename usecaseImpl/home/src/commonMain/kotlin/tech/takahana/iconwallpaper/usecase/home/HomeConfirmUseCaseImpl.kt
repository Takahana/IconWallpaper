package tech.takahana.iconwallpaper.usecase.home

import tech.takahana.iconwallpaper.repository.asset.SelectImageAssetRepository

class HomeConfirmUseCaseImpl(
    private val selectImageAssetRepository: SelectImageAssetRepository
) : HomeConfirmUseCase {
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

    override suspend fun recycleWallpaper() {
        selectImageAssetRepository.recycleImageAsset()
    }
}
