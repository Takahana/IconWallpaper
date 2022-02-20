package tech.takahana.iconwallpaper.uilogic.home

import kotlinx.coroutines.flow.SharedFlow

interface HomeUiLogic {

    val finishedHomeEffect: SharedFlow<Unit>

    suspend fun onClickedFinishButton()
}
