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
import tech.takahana.iconwallpaper.uilogic.home.SetWallpaperTargetUiModel
import tech.takahana.iconwallpaper.usecase.home.HomeConfirmUseCase
import tech.takahana.iconwallpaper.usecase.home.PlatformSetWallpaperTargetUseCaseModel
import tech.takahana.iconwallpaper.usecase.home.SetWallpaperTargetUseCaseModel

class HomeConfirmUiLogicImpl(
    private val viewModelScope: CoroutineScope,
    private val useCase: HomeConfirmUseCase,
    private val setWallpaperTargetMapper: AbstractMapper<SetWallpaperTargetUiModel, SetWallpaperTargetUseCaseModel> = PlatformSetWallpaperTargetMapper()
) : HomeConfirmUiLogic {

    private val mutableSetWallpaperTargetDialogSource = MutableStateFlow(false)

    private val mutableSetWallpaperEffect =
        MutableEffectSharedFlow<SetWallpaperTargetUiModel>()

    override val openSetWallpaperTargetDialogStateFlow: StateFlow<Boolean> =
        mutableSetWallpaperTargetDialogSource.asStateFlow()
    override val setWallpaperEffect: SharedFlow<SetWallpaperTargetUiModel> =
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

    override fun onClickedSetWallpaperTarget(target: SetWallpaperTargetUiModel) {
        viewModelScope.launch {
            useCase.selectSetWallpaperTarget(setWallpaperTargetMapper.mapToUseCaseModel(target))
                .onSuccess { target ->
                    mutableSetWallpaperEffect.emit(setWallpaperTargetMapper.mapToUiModel(target))
                    mutableSetWallpaperTargetDialogSource.value = false
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

    class PlatformSetWallpaperTargetMapper :
        AbstractMapper<SetWallpaperTargetUiModel, SetWallpaperTargetUseCaseModel>() {

        override fun mapToUseCaseModel(uiModel: Any): PlatformSetWallpaperTargetUseCaseModel {
            require(uiModel is PlatformSetWallpaperTargetUiModel)
            return uiModel.mapToUseCaseModel()
        }

        override fun mapToUiModel(useCaseModel: Any): PlatformSetWallpaperTargetUiModel {
            require(useCaseModel is PlatformSetWallpaperTargetUseCaseModel)
            return useCaseModel.mapToUiModel()
        }
    }

    /**
     * ??????????????????????????????????????????????????????????????????????????????Mapper
     * ????????????????????????Fake????????????????????????????????????????????????
     * ??????UiLogic????????????????????????????????????????????????????????????????????????????????????
     */
    abstract class AbstractMapper<UiModel, UseCaseModel> {

        abstract fun mapToUseCaseModel(uiModel: Any): UseCaseModel

        abstract fun mapToUiModel(useCaseModel: Any): UiModel
    }
}
