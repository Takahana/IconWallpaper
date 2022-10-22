package tech.takahana.iconwallpapaer.uilogic.home

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import tech.takahana.iconwallpaper.uilogic.home.HomeSelectImageAssetUiLogic
import tech.takahana.iconwallpaper.uilogic.home.ImageAssetUiModel
import tech.takahana.iconwallpaper.usecase.home.HomeSelectImageAssetUseCase
import tech.takahana.iconwallpaper.usecase.home.ImageAssetUseCaseModel

class HomeSelectImageAssetUiLogicImpl(
    private val viewModelScope: CoroutineScope,
    private val homeSelectImageAssetUseCase: HomeSelectImageAssetUseCase
) : HomeSelectImageAssetUiLogic {

    override val imageAssetListStateFlow: StateFlow<List<ImageAssetUiModel.AssetSelectable>> =

        homeSelectImageAssetUseCase.imageAssetListFlow.map { imageAssetList ->
            imageAssetList.filterIsInstance<ImageAssetUseCaseModel.HasAsset>().map { imageAsset ->
                ImageAssetUiModel.AssetSelectable(
                    imageAsset = imageAsset.asset,
                    isSelected = imageAsset.isSelected
                )
            }
        }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    override fun onClickedImageAsset(imageAsset: ImageAssetUiModel) {
        if (imageAsset is ImageAssetUiModel.AssetSelectable) {
            viewModelScope.launch {
                if (imageAsset.isSelected) {
                    homeSelectImageAssetUseCase.unselectImageAsset()
                } else {
                    homeSelectImageAssetUseCase.selectImageAsset(imageAsset.imageAsset)
                }
            }
        }
    }

    class Factory(
        private val homeSelectImageAssetUseCase: HomeSelectImageAssetUseCase
    ) : HomeSelectImageAssetUiLogic.Factory {
        override fun create(viewModelScope: CoroutineScope): HomeSelectImageAssetUiLogic {
            return HomeSelectImageAssetUiLogicImpl(viewModelScope, homeSelectImageAssetUseCase)
        }
    }
}
