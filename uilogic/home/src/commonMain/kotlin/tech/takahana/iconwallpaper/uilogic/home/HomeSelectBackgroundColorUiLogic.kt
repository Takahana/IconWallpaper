package tech.takahana.iconwallpaper.uilogic.home

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import tech.takahana.iconwallpaper.shared.domain.domainobject.ColorType

/**
 * パターン選択ページのUiLogic
 */
interface HomeSelectBackgroundColorUiLogic {

    val backgroundColorStateFlow: StateFlow<ColorType>

    fun onClickedBackgroundColor(colorType: ColorType)

    interface Factory {
        fun create(viewModelScope: CoroutineScope): HomeSelectBackgroundColorUiLogic
    }
}
