package tech.takahana.iconwallpapaer.uilogic.welcome

import kotlinx.coroutines.flow.SharedFlow
import tech.takahana.iconwallpaper.uilogic.welcome.WelcomeUiLogic
import tech.takahana.iconwallpaper.usecase.onboarding.WelcomeUseCase

class WelcomeUiLogicImpl(
    private val useCase: WelcomeUseCase
) : WelcomeUiLogic {

    override val finishedOnBoardingEffect: SharedFlow<Unit>
        get() = useCase.onFinishOnBoardingFlow

    override suspend fun onClickedFinishButton() {
        useCase.finishOnBoarding()
    }
}