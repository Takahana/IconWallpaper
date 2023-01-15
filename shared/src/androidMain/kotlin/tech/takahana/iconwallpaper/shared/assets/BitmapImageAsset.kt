package tech.takahana.iconwallpaper.shared.assets

import android.graphics.Bitmap
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetId
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetName
import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset

actual data class BitmapImageAsset actual constructor(
    override val id: AssetId,
    override val name: AssetName,
) : ImageAsset {

    lateinit var bitmap: Bitmap
        private set

    constructor(id: AssetId, name: AssetName, bitmap: Bitmap) : this(id, name) {
        this.bitmap = bitmap
    }

    actual fun recycle() {
        if (::bitmap.isInitialized) {
            bitmap.recycle()
        }
    }
}
