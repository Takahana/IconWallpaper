package tech.takahana.iconwallpapaer.uilogic.welcome

import com.futuremind.koru.ToNativeClass
import com.futuremind.koru.ToNativeInterface
import kotlinx.coroutines.flow.SharedFlow
import tech.takahana.iconwallpaper.uilogic.welcome.WelcomeUiLogic
import tech.takahana.iconwallpaper.usecase.onboarding.WelcomeUseCase

@ToNativeClass(name = "WelcomeUiLogicImplIos")
@ToNativeInterface(name = "WelcomeUiLogicIosProtocol")
class WelcomeUiLogicImpl(
    private val useCase: WelcomeUseCase
) : WelcomeUiLogic {

    override val finishedOnBoardingEffect: SharedFlow<Unit>
        get() = useCase.onFinishOnBoardingFlow

    override suspend fun onClickedFinishButton() {
        useCase.finishOnBoarding()
    }
}