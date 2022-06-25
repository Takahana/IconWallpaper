package tech.takahana.iconwallpapaer.uilogic.home

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import tech.takahana.iconwallpaper.shared.domain.domainobject.ColorType
import tech.takahana.iconwallpaper.uilogic.home.HomeSelectBackgroundColorUiLogic
import tech.takahana.iconwallpaper.usecase.home.HomeSelectPatternUseCase

class HomeSelectBackgroundColorUiLogicImpl(
    private val viewModelScope: CoroutineScope,
    private val homeSelectPatternUseCase: HomeSelectPatternUseCase
) : HomeSelectBackgroundColorUiLogic {

    override val backgroundColorStateFlow: StateFlow<ColorType> =
        homeSelectPatternUseCase.selectedBackgroundColorFlow.map { selectedBackgroundColorUseCaseModel ->
            selectedBackgroundColorUseCaseModel.backgroundColor
        }.stateIn(
            viewModelScope, SharingStarted.Eagerly,
            ColorType.Other(0xffb2dfdb)
        )

    override fun onClickedBackgroundColor(colorType: ColorType) {
        viewModelScope.launch {
            homeSelectPatternUseCase.selectBackgroundColor(colorType)
        }
    }

    class Factory(
        private val homeSelectPatternUseCase: HomeSelectPatternUseCase
    ) : HomeSelectBackgroundColorUiLogic.Factory {
        override fun create(viewModelScope: CoroutineScope): HomeSelectBackgroundColorUiLogic {
            return HomeSelectBackgroundColorUiLogicImpl(viewModelScope, homeSelectPatternUseCase)
        }
    }
}
