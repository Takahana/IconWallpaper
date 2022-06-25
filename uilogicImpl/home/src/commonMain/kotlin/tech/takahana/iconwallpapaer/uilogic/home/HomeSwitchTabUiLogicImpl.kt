package tech.takahana.iconwallpapaer.uilogic.home

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import tech.takahana.iconwallpaper.uilogic.home.HomeSwitchTabUiLogic
import tech.takahana.iconwallpaper.uilogic.home.SwitchTabUiModel
import tech.takahana.iconwallpaper.usecase.home.HomeSelectPatternUseCase
import tech.takahana.iconwallpaper.usecase.home.SwitchTabUseCaseModel

class HomeSwitchTabUiLogicImpl(
    private val viewModelScope: CoroutineScope,
    private val homeSelectPatternUseCase: HomeSelectPatternUseCase
) : HomeSwitchTabUiLogic {

    private val mutableSwitchTabStateFlow = MutableStateFlow(SwitchTabUiModel.PATTERN)

    override val switchTabStateFlow: StateFlow<SwitchTabUiModel> =
        mutableSwitchTabStateFlow.asStateFlow()

    override fun onClickedTab(switchTab: SwitchTabUiModel) {
        viewModelScope.launch {
            homeSelectPatternUseCase.changeTab(
                when (switchTab) {
                    SwitchTabUiModel.PATTERN -> SwitchTabUseCaseModel.PATTERN
                    SwitchTabUiModel.BACKGROUNDCOLOR -> SwitchTabUseCaseModel.BACKGROUNDCOLOR
                }
            ).fold(
                onSuccess = {
                    mutableSwitchTabStateFlow.value =
                        when (it) {
                            SwitchTabUseCaseModel.PATTERN -> SwitchTabUiModel.PATTERN
                            SwitchTabUseCaseModel.BACKGROUNDCOLOR -> SwitchTabUiModel.BACKGROUNDCOLOR
                        }
                },
                onFailure = { /* no-op */ }
            )
        }
    }

    class Factory(
        private val homeSelectPatternUseCase: HomeSelectPatternUseCase
    ) : HomeSwitchTabUiLogic.Factory {
        override fun create(viewModelScope: CoroutineScope): HomeSwitchTabUiLogic {
            return HomeSwitchTabUiLogicImpl(viewModelScope, homeSelectPatternUseCase)
        }
    }
}
