import tech.takahana.iconwallpaper.uilogic.home.PlatformSetWallpaperTargetUiModel
import tech.takahana.iconwallpaper.usecase.home.PlatformSetWallpaperTargetUseCaseModel

expect fun PlatformSetWallpaperTargetUiModel.mapToUseCaseModel(): PlatformSetWallpaperTargetUseCaseModel

expect fun PlatformSetWallpaperTargetUseCaseModel.mapToUiModel(): PlatformSetWallpaperTargetUiModel
