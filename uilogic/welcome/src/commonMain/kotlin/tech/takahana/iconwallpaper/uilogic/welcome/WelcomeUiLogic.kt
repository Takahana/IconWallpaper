package tech.takahana.iconwallpaper.uilogic.welcome

import kotlinx.coroutines.flow.SharedFlow

interface WelcomeUiLogic {

    val finishedOnBoardingEffect: SharedFlow<Unit>

    suspend fun onClickedFinishButton()
}
