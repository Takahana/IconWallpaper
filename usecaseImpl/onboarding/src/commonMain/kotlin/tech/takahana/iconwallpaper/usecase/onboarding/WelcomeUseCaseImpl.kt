package tech.takahana.iconwallpaper.usecase.onboarding

import com.futuremind.koru.ToNativeClass
import tech.takahana.iconwallpaper.shared.coroutines.flow.MutableSharedFlowReplayable

@ToNativeClass(name = "WelcomeUseCaseImplIos")
class WelcomeUseCaseImpl : WelcomeUseCase {

    override val onFinishOnBoardingFlow = MutableSharedFlowReplayable<Unit>()

    override suspend fun finishOnBoarding() {
        onFinishOnBoardingFlow.emit(Unit)
    }
}