package tech.takahana.iconwallpaper.repository.asset

import tech.takahana.iconwallpaper.shared.assets.BitmapImageAsset
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetId

/**
 * 写真から切り抜いた画像素材のRepository
 */
interface BitmapImageAssetRepository {

    fun setBitmap(bitmapImageAsset: BitmapImageAsset)

    fun getBitmap(id: AssetId): BitmapImageAsset?
}
