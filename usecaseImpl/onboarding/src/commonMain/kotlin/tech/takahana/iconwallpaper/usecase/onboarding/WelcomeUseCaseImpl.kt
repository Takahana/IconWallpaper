package tech.takahana.iconwallpaper.usecase.onboarding

import tech.takahana.iconwallpaper.shared.coroutines.flow.MutableSharedFlowReplayable

class WelcomeUseCaseImpl : WelcomeUseCase {

    override val onFinishOnBoardingFlow = MutableSharedFlowReplayable<Unit>()

    override suspend fun finishOnBoarding() {
        onFinishOnBoardingFlow.emit(Unit)
    }
}