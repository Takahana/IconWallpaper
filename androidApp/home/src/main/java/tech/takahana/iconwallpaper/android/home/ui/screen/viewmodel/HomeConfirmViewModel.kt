package tech.takahana.iconwallpaper.android.home.ui.screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import tech.takahana.iconwallpaper.uilogic.home.HomeConfirmUiLogic
import javax.inject.Inject

@HiltViewModel
class HomeConfirmViewModel @Inject constructor(
    private val uiLogicFactory: HomeConfirmUiLogic.Factory
) : ViewModel() {

    val uiLogic: HomeConfirmUiLogic by lazy {
        uiLogicFactory.create(viewModelScope)
    }
}
