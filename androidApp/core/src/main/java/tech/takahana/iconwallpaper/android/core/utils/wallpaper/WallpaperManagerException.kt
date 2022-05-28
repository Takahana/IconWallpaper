package tech.takahana.iconwallpaper.android.core.utils.wallpaper

/**
 * [WallpaperManagerWrapper]で扱うException
 */
sealed class WallpaperManagerException(
    override val message: String?,
    override val cause: Throwable?,
) : Exception(message, cause) {

    /**
     * WallpaperManagerへのアクセスに失敗した。
     */
    class FailedAccessWallManagerException() : WallpaperManagerException(
        message = "Failed to access WallpaperManager.",
        cause = null,
    )

    /**
     * 壁紙をセットする権限がなかった。
     */
    class NoPermissionSetWallpaperException() : WallpaperManagerException(
        message = "No permission to set wallpaper.",
        cause = null,
    )

    /**
     * WallpaperManagerException以外のExceptionをラップしたもの。
     */
    class WrappedException(
        exception: Exception,
    ) : WallpaperManagerException(exception.message, exception)
}
