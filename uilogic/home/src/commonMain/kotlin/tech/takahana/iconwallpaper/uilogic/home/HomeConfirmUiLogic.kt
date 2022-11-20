package tech.takahana.iconwallpaper.uilogic.home

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

interface HomeConfirmUiLogic {

    val openSetWallpaperTargetDialogStateFlow: StateFlow<Boolean>

    val setWallpaperEffect: SharedFlow<SetWallpaperTargetUiModel>

    fun onClickedSetWallpaper()

    fun onSetWallpaperTargetDialogDismissRequested()

    fun onClickedSetWallpaperTarget(
        target: SetWallpaperTargetUiModel
    )

    fun onSuccessSetWallPaper()

    interface Factory {
        fun create(viewModelScope: CoroutineScope): HomeConfirmUiLogic
    }
}

// TODO uilogic-fakeモジュールを作り、そこに移動させる
class FakeHomeConfirmUiLogic : HomeConfirmUiLogic {

    var openSetWallpaperTargetDialogStateFlowImpl = MutableStateFlow(false)

    var setWallpaperEffectImpl = MutableSharedFlow<PlatformSetWallpaperTargetUiModel>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    var onClickedSetWallpaperTargetImpl: (SetWallpaperTargetUiModel) -> Unit = {}

    override val openSetWallpaperTargetDialogStateFlow: StateFlow<Boolean> =
        openSetWallpaperTargetDialogStateFlowImpl.asStateFlow()

    override val setWallpaperEffect: SharedFlow<PlatformSetWallpaperTargetUiModel> =
        setWallpaperEffectImpl.asSharedFlow()

    override fun onClickedSetWallpaper() {
        openSetWallpaperTargetDialogStateFlowImpl.value = true
    }

    override fun onSetWallpaperTargetDialogDismissRequested() {
        openSetWallpaperTargetDialogStateFlowImpl.value = false
    }

    override fun onClickedSetWallpaperTarget(target: SetWallpaperTargetUiModel) =
        onClickedSetWallpaperTargetImpl(target)

    override fun onSuccessSetWallPaper() {}
}
