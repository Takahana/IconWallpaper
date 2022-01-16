package tech.takahana.iconwallpaper.usecase.home

import kotlinx.coroutines.flow.SharedFlow

interface HomeUseCase {

    /**
     * @return オンボーディングが終了したらUnitを放出するSharedFlow
     */
    val onFinishHomeFlow: SharedFlow<Unit>

    /**
     * オンボーディングを終了する
     */
    suspend fun finishHome()
}