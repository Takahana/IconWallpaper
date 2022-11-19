package tech.takahana.iconwallpapaer.uilogic.home

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import tech.takahana.iconwallpaper.shared.domain.domainobject.PatternType
import tech.takahana.iconwallpaper.uilogic.home.HomeSelectPatternUiLogic
import tech.takahana.iconwallpaper.uilogic.home.ImageAssetUiModel
import tech.takahana.iconwallpaper.usecase.home.HomeSelectPatternUseCase
import tech.takahana.iconwallpaper.usecase.home.ImageAssetUseCaseModel

class HomeSelectPatternUiLogicImpl(
    private val viewModelScope: CoroutineScope,
    private val homeSelectPatternUseCase: HomeSelectPatternUseCase
) : HomeSelectPatternUiLogic {

    override val patternTypeStateFlow: StateFlow<PatternType> =
        homeSelectPatternUseCase.selectedPatternFlow.map { selectedPatternUseCaseModel ->
            selectedPatternUseCaseModel.pattern
        }.stateIn(
            viewModelScope, SharingStarted.Eagerly,
            PatternType.SMALL
        )

    override val selectedImageAssetStateFlow: StateFlow<ImageAssetUiModel> =
        homeSelectPatternUseCase.selectedImageAssetFlow.map { selectedImageAssetUseCaseModel ->
            when (selectedImageAssetUseCaseModel) {
                is ImageAssetUseCaseModel.HasAsset -> {
                    ImageAssetUiModel.AssetSelectable(
                        imageAsset = selectedImageAssetUseCaseModel.asset,
                        isSelected = selectedImageAssetUseCaseModel.isSelected
                    )
                }
                ImageAssetUseCaseModel.None -> null
            }
        }.filterIsInstance<ImageAssetUiModel.AssetSelectable>()
            .stateIn(
                viewModelScope, SharingStarted.Eagerly,
                ImageAssetUiModel.None
            )

    override fun onClickedPattern(patternType: PatternType) {
        viewModelScope.launch {
            homeSelectPatternUseCase.selectPattern(patternType)
        }
    }

    class Factory(
        private val homeSelectPatternUseCase: HomeSelectPatternUseCase
    ) : HomeSelectPatternUiLogic.Factory {
        override fun create(viewModelScope: CoroutineScope): HomeSelectPatternUiLogic {
            return HomeSelectPatternUiLogicImpl(viewModelScope, homeSelectPatternUseCase)
        }
    }
}
