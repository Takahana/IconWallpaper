package tech.takahana.iconwallpapaer.uilogic.home

import kotlinx.coroutines.flow.SharedFlow
import tech.takahana.iconwallpaper.uilogic.home.HomeUiLogic

class HomeUiLogicImpl() : HomeUiLogic {

    override val finishedHomeEffect: SharedFlow<Unit>
        get() = TODO()

    override suspend fun onClickedFinishButton() {
        TODO()
    }
}
