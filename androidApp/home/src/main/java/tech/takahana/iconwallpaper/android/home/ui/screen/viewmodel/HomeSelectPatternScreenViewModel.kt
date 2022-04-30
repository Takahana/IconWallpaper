package tech.takahana.iconwallpaper.android.home.ui.screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import tech.takahana.iconwallpaper.uilogic.home.HomeSelectPatternUiLogic
import javax.inject.Inject

@HiltViewModel
class HomeSelectPatternScreenViewModel @Inject constructor(
    private val uiLogicFactory: HomeSelectPatternUiLogic.Factory
) : ViewModel() {

    val uiLogic: HomeSelectPatternUiLogic by lazy {
        uiLogicFactory.create(viewModelScope)
    }
}
