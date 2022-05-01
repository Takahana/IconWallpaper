package tech.takahana.iconwallpaper.uilogic.home

import kotlinx.coroutines.flow.StateFlow

/**
 * ホーム画面のタブ選択のUiLogic
 */
interface HomeSwitchTabUiLogic {

    val switchTabStateFlow: StateFlow<SwitchTabUiModel>

    fun onClickedTab(switchTab: SwitchTabUiModel)
}
