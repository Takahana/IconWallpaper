package tech.takahana.iconwallpapaer.uilogic.home

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import tech.takahana.iconwallpaper.shared.VisibleForTesting
import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset
import tech.takahana.iconwallpaper.uilogic.home.HomeSelectImageAssetUiLogic
import tech.takahana.iconwallpaper.uilogic.home.ImageAssetUiModel
import tech.takahana.iconwallpaper.usecase.home.HomeSelectImageAssetUseCase

class HomeSelectImageAssetUiLogicImpl(
    private val viewModelScope: CoroutineScope,
    private val homeSelectImageAssetUseCase: HomeSelectImageAssetUseCase
) : HomeSelectImageAssetUiLogic {

    @VisibleForTesting
    val mutableImageAssetListSource = MutableStateFlow<List<ImageAsset>>(emptyList())

    override val imageAssetListStateFlow: StateFlow<List<ImageAssetUiModel>> =
        combine(
            mutableImageAssetListSource,
            homeSelectImageAssetUseCase.selectedImageAssetFlow
        ) { imageAssetList, selectedImageAsset ->
            imageAssetList.map { imageAsset ->
                ImageAssetUiModel(
                    imageAsset = imageAsset,
                    isSelected = imageAsset.id == selectedImageAsset.asset?.id
                )
            }
        }
            .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    override fun onCreatedScreen() {
        viewModelScope.launch {
            val imageAssetList = homeSelectImageAssetUseCase.getAllImageAsset()
            mutableImageAssetListSource.value = imageAssetList
        }
    }

    override fun onClickedImageAsset(imageAsset: ImageAssetUiModel) {
        viewModelScope.launch {
            if (imageAsset.isSelected) {
                homeSelectImageAssetUseCase.unselectImageAsset()
            } else {
                homeSelectImageAssetUseCase.selectImageAsset(imageAsset.imageAsset)
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