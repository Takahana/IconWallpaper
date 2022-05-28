package tech.takahana.iconwallpapaer.uilogic.home

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mapToUiModel
import mapToUseCaseModel
import tech.takahana.iconwallpaper.shared.coroutines.flow.MutableEffectSharedFlow
import tech.takahana.iconwallpaper.uilogic.home.HomeConfirmUiLogic
import tech.takahana.iconwallpaper.uilogic.home.PlatformSetWallpaperTargetUiModel
import tech.takahana.iconwallpaper.usecase.home.HomeConfirmUseCase
import tech.takahana.iconwallpaper.usecase.home.PlatformSetWallpaperTargetUseCaseModel

class HomeConfirmUiLogicImpl(
    private val viewModelScope: CoroutineScope,
    private val useCase: HomeConfirmUseCase,
) : HomeConfirmUiLogic {

    private val mutableSetWallpaperTargetDialogSource = MutableStateFlow(false)

    private val mutableSetWallpaperEffect =
        MutableEffectSharedFlow<PlatformSetWallpaperTargetUiModel>()

    override val openSetWallpaperTargetDialogStateFlow: StateFlow<Boolean> =
        mutableSetWallpaperTargetDialogSource.asStateFlow()
    override val setWallpaperEffect: SharedFlow<PlatformSetWallpaperTargetUiModel> =
        mutableSetWallpaperEffect.asSharedFlow()

    override fun onClickedSetWallpaper() {
        useCase.setWallpaper()
            .onSuccess {
                mutableSetWallpaperTargetDialogSource.value = true
            }
    }

    override fun onSetWallpaperTargetDialogDismissRequested() {
        useCase.cancelSetWallpaper()
            .onSuccess {
                mutableSetWallpaperTargetDialogSource.value = false
            }
    }

    override fun onClickedSetWallpaperTarget(target: PlatformSetWallpaperTargetUiModel) {
        viewModelScope.launch {
            useCase.selectSetWallpaperTarget(target.mapToUseCaseModel())
                .onSuccess { target ->
                    (target as? PlatformSetWallpaperTargetUseCaseModel)?.let {
                        mutableSetWallpaperEffect.emit(target.mapToUiModel())
                        mutableSetWallpaperTargetDialogSource.value = false
                    }
                }
        }
    }

    class Factory(
        private val useCase: HomeConfirmUseCase,
    ) : HomeConfirmUiLogic.Factory {
        override fun create(viewModelScope: CoroutineScope): HomeConfirmUiLogic {
            return HomeConfirmUiLogicImpl(viewModelScope, useCase)
        }
    }
}
