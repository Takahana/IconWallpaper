package tech.takahana.iconwallpaper.shared

import android.graphics.Bitmap
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import tech.takahana.iconwallpaper.shared.assets.BitmapImageAsset
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetId
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetName

class BitmapImageAssetTest {

    private lateinit var bitmapImageAsset: BitmapImageAsset
    private lateinit var mockBitmap: Bitmap

    @Before
    fun before() {
        mockBitmap = mockk()
        bitmapImageAsset = BitmapImageAsset(
            id = AssetId("assetId"), name = AssetName("assetName"), bitmap = mockBitmap
        )
    }

    @Test
    fun recycleBitmap() {
        every { mockBitmap.recycle() } returns Unit

        bitmapImageAsset.recycle()

        verify { mockBitmap.recycle() }
    }
}
