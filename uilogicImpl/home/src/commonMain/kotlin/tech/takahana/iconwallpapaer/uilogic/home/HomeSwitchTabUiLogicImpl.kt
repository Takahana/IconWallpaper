package tech.takahana.iconwallpapaer.uilogic.home

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import tech.takahana.iconwallpaper.uilogic.home.HomeSwitchTabUiLogic
import tech.takahana.iconwallpaper.uilogic.home.SwitchTabUiModel

class HomeSwitchTabUiLogicImpl : HomeSwitchTabUiLogic {

    private val mutableSwitchTabStateFlow = MutableStateFlow(SwitchTabUiModel.PATTERN)

    override val switchTabStateFlow: StateFlow<SwitchTabUiModel> =
        mutableSwitchTabStateFlow.asStateFlow()

    override fun onClickedTab(switchTab: SwitchTabUiModel) {
        mutableSwitchTabStateFlow.value = switchTab
    }
}
