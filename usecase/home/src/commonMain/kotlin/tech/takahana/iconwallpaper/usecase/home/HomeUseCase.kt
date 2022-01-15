package tech.takahana.iconwallpaper.usecase.home

import com.futuremind.koru.ToNativeInterface
import kotlinx.coroutines.flow.SharedFlow

@ToNativeInterface(name = "HomeUseCaseIosProtocol")
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