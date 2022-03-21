package tech.takahana.iconwallpapaer.uilogic.home

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import tech.takahana.iconwallpaper.shared.domain.domainobject.PatternType
import tech.takahana.iconwallpaper.uilogic.home.HomeSelectPatternUiLogic
import tech.takahana.iconwallpaper.usecase.home.HomeSelectPatternUseCase

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
