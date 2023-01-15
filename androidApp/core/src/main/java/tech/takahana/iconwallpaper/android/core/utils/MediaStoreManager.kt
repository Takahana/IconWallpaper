package tech.takahana.iconwallpaper.android.core.utils

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.ContextCompat
import java.io.FileNotFoundException
import java.util.Calendar
import java.util.TimeZone

/**
 * MediaStoreへのアクセスを行うManager。
 *
 * このクラス内で扱っているグループという概念は、コレクション以下に作られるディレクトリのようなもの。
 * グループを指定することで、任意のグループのファイルをまとめて削除できるようにしている。
 */
class MediaStoreManager(
    private val applicationContext: Context
) {

    /**
     * 画像コレクション以下に画像を保存する。
     *
     * @param bitmap [android.graphics.Bitmap]
     * @param group 画像コレクション以下に作るグループ
     */
    fun saveToMediaImages(
        bitmap: Bitmap,
        group: Group = Group.Default,
    ): Uri {

        // Android 10 以上なら Manifest.permission.WRITE_EXTERNAL_STORAGE の権限がなくても、
        // MediaStore経由でストレージに書き込める
        val canSaveImage =
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q || ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED

        if (!canSaveImage) {
            Log.e(
                "MediaStoreManager",
                "Permission denied. Manifest.permission.WRITE_EXTERNAL_STORAGE"
            )
            return Uri.EMPTY
        }

        val resolver = applicationContext.contentResolver

        val imageCollection = getImageCollection()

        val contentValues = ContentValues().apply {
            put(
                MediaStore.Images.Media.DISPLAY_NAME,
                createMediaImagesDisplayName(group)
            )
            put(
                MediaStore.Images.Media.MIME_TYPE,
                "image/png"
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(
                    MediaStore.Images.Media.RELATIVE_PATH,
                    "Pictures/${applicationContext.packageName}/${group.value}"
                )
                put(MediaStore.Images.Media.IS_PENDING, 1)
            }
        }

        val contentUri = resolver.insert(imageCollection, contentValues) ?: return Uri.EMPTY

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

        return contentUri
    }

    /**
     * 画像コレクション以下の指定したグループを削除する。
     *
     * @param group 画像コレクション以下のグループ。
     */
    fun delete(
        group: Group = Group.Default,
    ) {
        val resolver = applicationContext.contentResolver
        val selection =
            "${MediaStore.Images.Media.DISPLAY_NAME} LIKE ?"
        val selectionArgs = arrayOf(
            "%${getHashCode(group.value)}%"
        )

        resolver.delete(
            getImageCollection(),
            selection,
            selectionArgs,
        )
    }

    private fun getImageCollection(): Uri {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }
    }

    /**
     * ファイルの表示名を生成する。
     *
     * @param group 画像コレクション以下のグループ
     */
    private fun createMediaImagesDisplayName(
        group: Group,
    ): String {
        val timeZone = TimeZone.getDefault()
        val calendar = Calendar.getInstance(timeZone)
        val time = calendar.time.time.toString()
        return "${getHashCode(group.value)}$time.png"
    }

    /**
     * アプリケーションのパッケージ名を元にhashCodeを生成する。
     */
    private fun getHashCode(extra: String): String {
        return "${applicationContext.packageName.hashCode()}${extra.hashCode()}"
    }

    enum class Group(val value: String) {
        // デフォルトの保存先
        Default("default"),

        // ユーザが壁紙を保存するアクションをした時の保存先
        Output("output"),

        // ユーザが壁紙を保存するアクションをした時の保存先
        TmpForSetWallpaperByOtherApp("TmpForSetWallpaperByOtherApp"),
    }
}
