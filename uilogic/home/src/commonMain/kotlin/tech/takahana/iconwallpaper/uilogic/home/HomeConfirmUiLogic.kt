package tech.takahana.iconwallpaper.uilogic.home

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface HomeConfirmUiLogic {

    val openSetWallpaperTargetDialogStateFlow: StateFlow<Boolean>

    val setWallpaperEffect: SharedFlow<SetWallpaperTargetUiModel>

    fun onClickedSetWallpaper()

    fun onSetWallpaperTargetDialogDismissRequested()

    fun onClickedSetWallpaperTarget(
        target: SetWallpaperTargetUiModel
    )

    interface Factory {
        fun create(viewModelScope: CoroutineScope): HomeConfirmUiLogic
    }
}
