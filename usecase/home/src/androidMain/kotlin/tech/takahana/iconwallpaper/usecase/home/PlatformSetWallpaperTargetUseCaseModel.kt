package tech.takahana.iconwallpaper.usecase.home

/**
 * 壁紙をセットする対象のUseCaseModel
 */
actual sealed class PlatformSetWallpaperTargetUseCaseModel : SetWallpaperTargetUseCaseModel {
    object Home : PlatformSetWallpaperTargetUseCaseModel()
    object Lock : PlatformSetWallpaperTargetUseCaseModel()
    object HomeAndLock : PlatformSetWallpaperTargetUseCaseModel()
    object OtherApp : PlatformSetWallpaperTargetUseCaseModel()
}
