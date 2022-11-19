package tech.takahana.iconwallpaper.android.domain.repository

import tech.takahana.iconwallpaper.repository.asset.BitmapImageAssetRepository
import tech.takahana.iconwallpaper.shared.assets.BitmapImageAsset
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetId

class BitmapImageAssetRepositoryImpl : BitmapImageAssetRepository {

    private val bitmapMap = mutableMapOf<AssetId, BitmapImageAsset>()

    override fun setBitmap(bitmapImageAsset: BitmapImageAsset) {
        bitmapMap[bitmapImageAsset.id] = bitmapImageAsset
    }

    override fun getBitmap(id: AssetId): BitmapImageAsset? {
        return bitmapMap[id]
    }

    override fun recycleBitmap(id: AssetId) {
        bitmapMap[id]?.bitmap?.recycle()
    }
}
