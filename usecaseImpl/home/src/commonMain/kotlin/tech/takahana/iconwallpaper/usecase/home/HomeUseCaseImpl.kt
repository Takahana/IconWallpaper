package tech.takahana.iconwallpaper.usecase.home

import com.futuremind.koru.ToNativeClass
import tech.takahana.iconwallpaper.shared.coroutines.flow.MutableSharedFlowReplayable
import tech.takahana.iconwallpaper.usecase.onboarding.HomeUseCase

@ToNativeClass(name = "HomeUseCaseImplIos")
class HomeUseCaseImpl : HomeUseCase {

    override val onFinishHomeFlow = MutableSharedFlowReplayable<Unit>()

    override suspend fun finishHome() {
        onFinishHomeFlow.emit(Unit)
    }
}