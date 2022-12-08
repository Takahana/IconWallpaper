package tech.takahana.iconwallpapaer.uilogic.home

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import mapToUiModel
import mapToUseCaseModel
import tech.takahana.iconwallpaper.shared.coroutines.flow.MutableEffectSharedFlow
import tech.takahana.iconwallpaper.shared.domain.domainobject.ColorType
import tech.takahana.iconwallpaper.shared.domain.domainobject.PatternType
import tech.takahana.iconwallpaper.uilogic.home.HomeConfirmUiLogic
import tech.takahana.iconwallpaper.uilogic.home.ImageAssetUiModel
import tech.takahana.iconwallpaper.uilogic.home.PlatformSetWallpaperTargetUiModel
import tech.takahana.iconwallpaper.uilogic.home.SetWallpaperTargetUiModel
import tech.takahana.iconwallpaper.usecase.home.HomeConfirmUseCase
import tech.takahana.iconwallpaper.usecase.home.ImageAssetUseCaseModel
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

    override val patternTypeStateFlow: StateFlow<PatternType> =
        useCase.selectedPatternFlow.map { selectedPatternUseCaseModel ->
            selectedPatternUseCaseModel.pattern
        }.stateIn(
            viewModelScope, SharingStarted.Eagerly,
            PatternType.SMALL
        )

    override val selectedImageAssetStateFlow: StateFlow<ImageAssetUiModel> =
        useCase.selectedImageAssetFlow.map { selectedImageAssetUseCaseModel ->
            when (selectedImageAssetUseCaseModel) {
                is ImageAssetUseCaseModel.HasAsset -> {
                    ImageAssetUiModel.AssetSelectable(
                        imageAsset = selectedImageAssetUseCaseModel.asset,
                        isSelected = selectedImageAssetUseCaseModel.isSelected
                    )
                }
                ImageAssetUseCaseModel.None -> ImageAssetUiModel.None
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            ImageAssetUiModel.None
        )

    override val backgroundColorStateFlow: StateFlow<ColorType> =
        useCase.selectedBackgroundColorFlow.map { selectedBackgroundColor ->
            selectedBackgroundColor.backgroundColor
        }.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            ColorType.Other(0xffffff)
        )

    override fun onClickedSetWallpaper() {
        viewModelScope.launch {
            useCase.setWallpaper()
                .onSuccess {
                    mutableSetWallpaperTargetDialogSource.value = true
                }
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

    override fun onSuccessSetWallPaper() {
        viewModelScope.launch {
            useCase.recycleWallpaper()
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
     * 各プラットフォームのマッピングの差異を吸収するためのMapper
     * テストする時にもFakeのクラスをマッピングする時に使う
     * 他のUiLogicでも似たケースがあれば共通処理として切り出してもいいかも
     */
    abstract class AbstractMapper<UiModel, UseCaseModel> {

        abstract fun mapToUseCaseModel(uiModel: Any): UseCaseModel

        abstract fun mapToUiModel(useCaseModel: Any): UiModel
    }
}
