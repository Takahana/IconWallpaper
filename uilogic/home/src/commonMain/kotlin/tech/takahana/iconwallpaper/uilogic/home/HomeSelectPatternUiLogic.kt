package tech.takahana.iconwallpaper.uilogic.home

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import tech.takahana.iconwallpaper.shared.domain.domainobject.PatternType

/**
 * パターン選択ページのUiLogic
 */
interface HomeSelectPatternUiLogic {

    val patternTypeStateFlow: StateFlow<PatternType>

    fun onClickedPattern(patternType: PatternType)

    interface Factory {
        fun create(viewModelScope: CoroutineScope): HomeSelectPatternUiLogic
    }
}
