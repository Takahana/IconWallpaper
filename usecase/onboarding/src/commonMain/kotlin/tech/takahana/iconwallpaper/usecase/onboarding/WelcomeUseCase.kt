package tech.takahana.iconwallpaper.usecase.onboarding

import kotlinx.coroutines.flow.SharedFlow

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