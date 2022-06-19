import tech.takahana.iconwallpaper.uilogic.home.PlatformSetWallpaperTargetUiModel
import tech.takahana.iconwallpaper.usecase.home.PlatformSetWallpaperTargetUseCaseModel

actual fun PlatformSetWallpaperTargetUiModel.mapToUseCaseModel(): PlatformSetWallpaperTargetUseCaseModel {
    return when (this) {
        PlatformSetWallpaperTargetUiModel.Home -> PlatformSetWallpaperTargetUseCaseModel.Home
        PlatformSetWallpaperTargetUiModel.Lock -> PlatformSetWallpaperTargetUseCaseModel.Lock
        PlatformSetWallpaperTargetUiModel.HomeAndLock -> PlatformSetWallpaperTargetUseCaseModel.HomeAndLock
        PlatformSetWallpaperTargetUiModel.OtherApp -> PlatformSetWallpaperTargetUseCaseModel.OtherApp
    }
}

actual fun PlatformSetWallpaperTargetUseCaseModel.mapToUiModel(): PlatformSetWallpaperTargetUiModel {
    return when (this) {
        PlatformSetWallpaperTargetUseCaseModel.Home -> PlatformSetWallpaperTargetUiModel.Home
        PlatformSetWallpaperTargetUseCaseModel.Lock -> PlatformSetWallpaperTargetUiModel.Lock
        PlatformSetWallpaperTargetUseCaseModel.HomeAndLock -> PlatformSetWallpaperTargetUiModel.HomeAndLock
        PlatformSetWallpaperTargetUseCaseModel.OtherApp -> PlatformSetWallpaperTargetUiModel.OtherApp
    }
}
