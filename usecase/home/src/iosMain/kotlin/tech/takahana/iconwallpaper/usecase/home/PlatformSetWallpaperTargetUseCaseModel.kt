package tech.takahana.iconwallpaper.usecase.home

/**
 * 壁紙をセットする対象のUseCaseModel
 */
actual sealed class PlatformSetWallpaperTargetUseCaseModel : SetWallpaperTargetUseCaseModel {
    object Unknown : PlatformSetWallpaperTargetUseCaseModel()
}
