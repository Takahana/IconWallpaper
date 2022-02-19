package tech.takahana.iconwallpaper.android.home.ui.screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import tech.takahana.iconwallpaper.uilogic.home.HomeSelectImageAssetUiLogic
import javax.inject.Inject

@HiltViewModel
class HomeSelectImageAssetViewModel @Inject constructor(
    private val uiLogicFactory: HomeSelectImageAssetUiLogic.Factory
) : ViewModel() {

    val uiLogic: HomeSelectImageAssetUiLogic by lazy {
        uiLogicFactory.create(viewModelScope)
    }
}