package tech.takahana.iconwallpaper.shared

import android.graphics.Bitmap
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import tech.takahana.iconwallpaper.shared.assets.BitmapImageAsset
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetId
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetName
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class BitmapImageAssetTest {
    @Test
    fun recycleBitmap() {
        val bitmapImageAsset = BitmapImageAsset(
            id = AssetId("assetId"),
            name = AssetName("assetName"),
            bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
        )
        val bitmap = bitmapImageAsset.bitmap
        assertTrue(!bitmap.isRecycled)
    }
}
