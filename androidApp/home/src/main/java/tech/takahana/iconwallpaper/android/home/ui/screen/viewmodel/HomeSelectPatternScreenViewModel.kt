package tech.takahana.iconwallpaper.android.home.ui.screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import tech.takahana.iconwallpaper.uilogic.home.HomeSelectPatternUiLogic
import tech.takahana.iconwallpaper.uilogic.home.HomeSwitchTabUiLogic
import javax.inject.Inject

@HiltViewModel
class HomeSelectPatternScreenViewModel @Inject constructor(
    private val selectPatternUiLogicFactory: HomeSelectPatternUiLogic.Factory,
    val switchTabUiLogic: HomeSwitchTabUiLogic
) : ViewModel() {

    val selectPatternUiLogic: HomeSelectPatternUiLogic by lazy {
        selectPatternUiLogicFactory.create(viewModelScope)
    }
}
