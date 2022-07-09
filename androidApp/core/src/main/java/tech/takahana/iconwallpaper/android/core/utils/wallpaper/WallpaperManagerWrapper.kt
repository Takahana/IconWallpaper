package tech.takahana.iconwallpaper.android.core.utils.wallpaper

import android.Manifest
import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.core.content.ContextCompat

class WallpaperManagerWrapper(
    private val applicationContext: Context,
) {

    private val wallpaperManager: WallpaperManager? by lazy {
        applicationContext.getSystemService(WallpaperManager::class.java)
    }

    fun setBitmapToLock(
        bitmap: Bitmap,
        onSuccess: (() -> Unit)? = null,
        onFailure: ((WallpaperManagerException) -> Unit)? = null,
    ) {
        setBitmap(bitmap, WallpaperManager.FLAG_LOCK, onSuccess, onFailure)
    }

    fun setBitmapToSystem(
        bitmap: Bitmap,
        onSuccess: (() -> Unit)? = null,
        onFailure: ((WallpaperManagerException) -> Unit)? = null,
    ) {
        setBitmap(bitmap, WallpaperManager.FLAG_SYSTEM, onSuccess, onFailure)
    }

    fun setBitmapToLockAndSystem(
        bitmap: Bitmap,
        onSuccess: (() -> Unit)? = null,
        onFailure: ((WallpaperManagerException) -> Unit)? = null,
    ) {
        setBitmapToLock(bitmap, onSuccess, onFailure)
        setBitmapToSystem(bitmap, onSuccess, onFailure)
    }

    /**
     * @return 壁紙をセットする権限があればtrue, それ以外はfalse
     */
    fun canSetWallpaper(): Boolean {
        return ContextCompat.checkSelfPermission(
            applicationContext,
            Manifest.permission.SET_WALLPAPER
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun getChooserIntentForSetWallpaper(): Intent {
        TODO("Intent.ACTION_ATTACH_DATAのIntentを作成する")
    }

    private fun setBitmap(
        bitmap: Bitmap,
        which: Int,
        onSuccess: (() -> Unit)? = null,
        onFailure: ((WallpaperManagerException) -> Unit)? = null,
    ) {
        if (!canSetWallpaper()) onFailure?.invoke(WallpaperManagerException.NoPermissionSetWallpaperException())

        if (wallpaperManager == null) onFailure?.invoke(WallpaperManagerException.FailedAccessWallManagerException())

        try {
            wallpaperManager?.setBitmap(bitmap, null, false, which)
            onSuccess?.invoke()
        } catch (exception: Exception) {
            onFailure?.invoke(WallpaperManagerException.WrappedException(exception))
        }
    }
}
