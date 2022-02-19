package tech.takahana.iconwallpaper.uilogic.home

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

/**
 * ホーム素材選択ページのUiLogic
 */
interface HomeSelectImageAssetUiLogic {

    val imageAssetListStateFlow: StateFlow<List<ImageAssetUiModel>>

    fun onCreatedScreen()

    fun onClickedImageAsset(imageAsset: ImageAssetUiModel)

    interface Factory {
        fun create(viewModelScope: CoroutineScope): HomeSelectImageAssetUiLogic
    }
}