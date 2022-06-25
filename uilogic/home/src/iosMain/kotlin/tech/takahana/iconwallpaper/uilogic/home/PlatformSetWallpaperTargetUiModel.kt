package tech.takahana.iconwallpaper.uilogic.home

/**
 * 壁紙をセットする対象のUiModel
 */
actual sealed class PlatformSetWallpaperTargetUiModel : SetWallpaperTargetUiModel {
    object Unknown : PlatformSetWallpaperTargetUiModel()
}
