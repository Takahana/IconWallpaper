package tech.takahana.iconwallpaper.uilogic.home

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import tech.takahana.iconwallpaper.shared.domain.domainobject.BackgroundColor

/**
 * パターン選択ページのUiLogic
 */
interface HomeSelectBackgroundColorUiLogic {

    val backgroundColorStateFlow: StateFlow<BackgroundColor>

    fun onClickedBackgroundColor(backgroundColor: BackgroundColor)

    interface Factory {
        fun create(viewModelScope: CoroutineScope): HomeSelectBackgroundColorUiLogic
    }
}
