package tech.takahana.iconwallpapaer.uilogic.home

import kotlinx.coroutines.flow.SharedFlow
import tech.takahana.iconwallpaper.uilogic.home.HomeUiLogic
import tech.takahana.iconwallpaper.usecase.home.HomeUseCase

class HomeUiLogicImpl(
    private val useCase: HomeUseCase
) : HomeUiLogic {

    override val finishedHomeEffect: SharedFlow<Unit>
        get() = useCase.onFinishHomeFlow

    override suspend fun onClickedFinishButton() {
        useCase.finishHome()
    }
}
