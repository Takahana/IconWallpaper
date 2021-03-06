package tech.takahana.iconwallpaper.android.home.ui.screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import tech.takahana.iconwallpaper.uilogic.home.HomeSelectBackgroundColorUiLogic
import tech.takahana.iconwallpaper.uilogic.home.HomeSelectPatternUiLogic
import tech.takahana.iconwallpaper.uilogic.home.HomeSwitchTabUiLogic
import javax.inject.Inject

@HiltViewModel
class HomeSelectPatternViewModel @Inject constructor(
    private val selectPatternUiLogicFactory: HomeSelectPatternUiLogic.Factory,
    private val selectBackgroundColorUiLogicFactory: HomeSelectBackgroundColorUiLogic.Factory,
    private val switchTabUiLogicFactory: HomeSwitchTabUiLogic.Factory
) : ViewModel() {

    val selectPatternUiLogic: HomeSelectPatternUiLogic by lazy {
        selectPatternUiLogicFactory.create(viewModelScope)
    }

    val selectBackgroundColorUiLogic: HomeSelectBackgroundColorUiLogic by lazy {
        selectBackgroundColorUiLogicFactory.create(viewModelScope)
    }

    val switchTabUiLogic: HomeSwitchTabUiLogic by lazy {
        switchTabUiLogicFactory.create(viewModelScope)
    }
}
