package tech.takahana.iconwallpaper.uilogic.home

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

/**
 * ホーム画面のタブ選択のUiLogic
 */
interface HomeSwitchTabUiLogic {

    val switchTabStateFlow: StateFlow<SwitchTabUiModel>

    fun onClickedTab(switchTab: SwitchTabUiModel)

    interface Factory {
        fun create(viewModelScope: CoroutineScope): HomeSwitchTabUiLogic
    }
}
