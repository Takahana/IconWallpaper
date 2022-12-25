package tech.takahana.iconwallpaper.repository.asset

import kotlinx.coroutines.flow.Flow
import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset

/**
 * 選択した画像素材のRepository
 */
interface SelectImageAssetRepository {

    /**
     * @return 選択済みの画像素材のFlow
     */
    val selectedImageAssetFlow: Flow<ImageAsset?>

    /**
     * 画像素材を選択済みとしてセットする
     *
     * @param imageAsset 追加する画像素材
     */
    suspend fun setSelectedImageAsset(imageAsset: ImageAsset)

    /**
     * 選択済みの画像素材をクリアする
     */
    suspend fun clearSelectedImageAsset()

    /**
     * 画像素材のビットマップをリサイクルする
     */
    fun recycleImageAsset()
}
