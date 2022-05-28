package tech.takahana.iconwallpaper.uilogic.home

/**
 * 壁紙をセットする対象のUiModel
 */
actual sealed class PlatformSetWallpaperTargetUiModel : SetWallpaperTargetUiModel {
    object Home : PlatformSetWallpaperTargetUiModel()
    object Lock : PlatformSetWallpaperTargetUiModel()
    object HomeAndLock : PlatformSetWallpaperTargetUiModel()
    object OtherApp : PlatformSetWallpaperTargetUiModel()
}
