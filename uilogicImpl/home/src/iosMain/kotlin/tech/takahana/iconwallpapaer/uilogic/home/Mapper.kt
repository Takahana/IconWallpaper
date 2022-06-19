import tech.takahana.iconwallpaper.uilogic.home.PlatformSetWallpaperTargetUiModel
import tech.takahana.iconwallpaper.usecase.home.PlatformSetWallpaperTargetUseCaseModel

actual fun PlatformSetWallpaperTargetUiModel.mapToUseCaseModel(): PlatformSetWallpaperTargetUseCaseModel {
    return when (this) {
        PlatformSetWallpaperTargetUiModel.Unknown -> PlatformSetWallpaperTargetUseCaseModel.Unknown
    }
}

actual fun PlatformSetWallpaperTargetUseCaseModel.mapToUiModel(): PlatformSetWallpaperTargetUiModel {
    return when (this) {
        PlatformSetWallpaperTargetUseCaseModel.Unknown -> PlatformSetWallpaperTargetUiModel.Unknown
    }
}
