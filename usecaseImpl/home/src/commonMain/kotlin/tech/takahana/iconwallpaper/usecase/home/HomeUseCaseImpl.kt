package tech.takahana.iconwallpaper.usecase.home

import tech.takahana.iconwallpaper.shared.coroutines.flow.MutableSharedFlowReplayable

class HomeUseCaseImpl : HomeUseCase {

    override val onFinishHomeFlow = MutableSharedFlowReplayable<Unit>()

    override suspend fun finishHome() {
        onFinishHomeFlow.emit(Unit)
    }
}