package tech.takahana.iconwallpaper.usecase.home

import kotlinx.coroutines.flow.Flow
import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset

/**
 * ホーム素材選択ページのUseCase(Stateless)
 */
interface HomeSelectImageAssetUseCase {

    /**
     * 選択済みの素材のFlow
     * 更新されたら新しいデータが放出される
     */
    val selectedImageAssetFlow: Flow<SelectedImageAssetUseCaseModel>

    /**
     * @return 全ての画像素材
     */
    suspend fun getAllImageAsset(): List<ImageAsset>

    /**
     * 素材を選択するユースケース
     */
    suspend fun selectImageAsset(imageAsset: ImageAsset)

    /**
     * 素材の選択を解除するユースケース
     */
    suspend fun unselectImageAsset()
}
