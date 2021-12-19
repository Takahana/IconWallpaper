package tech.takahana.iconwallpaper.usecase.onboarding

import com.futuremind.koru.ToNativeInterface
import kotlinx.coroutines.flow.SharedFlow

@ToNativeInterface(name = "WelcomeUseCaseIosProtocol")
interface WelcomeUseCase {

    /**
     * @return オンボーディングが終了したらUnitを放出するSharedFlow
     */
    val onFinishOnBoardingFlow: SharedFlow<Unit>

    /**
     * オンボーディングを終了する
     */
    suspend fun finishOnBoarding()
}