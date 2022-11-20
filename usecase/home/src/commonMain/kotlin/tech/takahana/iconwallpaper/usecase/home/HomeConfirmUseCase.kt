package tech.takahana.iconwallpaper.usecase.home

interface HomeConfirmUseCase {

    fun saveWallpaper(): Result<Unit>

    fun setWallpaper(): Result<Unit>

    fun cancelSetWallpaper(): Result<Unit>

    fun selectSetWallpaperTarget(target: SetWallpaperTargetUseCaseModel): Result<SetWallpaperTargetUseCaseModel>

    suspend fun recycleWallpaper()
}
