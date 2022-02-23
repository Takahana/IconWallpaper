package tech.takahana.iconwallpaper.repository.asset

import tech.takahana.iconwallpaper.shared.assets.LocalImageAsset

/**
 * ローカルの画像素材のRepository
 */
interface LocalImageAssetRepository {

    /**
     * @return ローカルの全ての画像素材
     */
    fun getAll(): List<LocalImageAsset>
}
