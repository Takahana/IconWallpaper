package tech.takahana.iconwallpaper.android.core.utils

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.ContextCompat
import java.io.FileNotFoundException
import java.util.*

class MediaStoreManager(
    private val applicationContext: Context
) {

    fun saveToMediaImages(bitmap: Bitmap) {

        // Android 10 以上なら Manifest.permission.WRITE_EXTERNAL_STORAGE の権限がなくても、
        // MediaStore経由でストレージに書き込める
        val canSaveImage =
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q ||
                    ContextCompat.checkSelfPermission(
                        applicationContext,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED

        if (!canSaveImage) {
            Log.e(
                "MediaStoreManager",
                "Permission denied. Manifest.permission.WRITE_EXTERNAL_STORAGE"
            )
            return
        }

        val resolver = applicationContext.contentResolver

        val imageCollection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }

        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, createMediaImagesDisplayName())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.Audio.Media.IS_PENDING, 1)
            }
        }

        val contentUri = resolver.insert(imageCollection, contentValues) ?: return

        try {
            resolver.openOutputStream(contentUri).use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 70, outputStream)
            }
        } catch (e: NullPointerException) {
            // no-op
        } catch (e: FileNotFoundException) {
            // no-op
        }

        contentValues.clear()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            contentValues.put(MediaStore.Audio.Media.IS_PENDING, 0)
        }
        resolver.update(contentUri, contentValues, null, null)

        bitmap.recycle()
    }

    private fun createMediaImagesDisplayName(): String {
        val timeZone = TimeZone.getDefault()
        val calendar = Calendar.getInstance(timeZone)
        val filename = calendar.time.time.toString()
        return "$filename.png"
    }
}