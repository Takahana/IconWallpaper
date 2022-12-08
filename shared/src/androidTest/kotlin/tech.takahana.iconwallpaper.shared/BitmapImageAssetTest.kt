package tech.takahana.iconwallpaper.shared

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import tech.takahana.iconwallpaper.shared.assets.BitmapImageAsset
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetId
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetName

class BitmapImageAssetTest {
    @Test
    fun recycleBitmap() {
        val bitmapImageAsset = BitmapImageAsset(
            id = AssetId("assetId"), name = AssetName("assetName"), bitmap = mockk()
        )
        val bitmap = bitmapImageAsset.bitmap
        every { bitmap.recycle() } returns Unit

        bitmap.recycle()

        verify { bitmap.recycle() }
    }
}
