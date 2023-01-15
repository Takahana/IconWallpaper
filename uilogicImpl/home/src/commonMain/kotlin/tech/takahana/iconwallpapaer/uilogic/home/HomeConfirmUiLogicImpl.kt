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

    private val mutableSaveWallpaperEffect = MutableEffectSharedFlow<Unit>()

    private val mutableOpenPermissionRequestRationaleDialogSource = MutableStateFlow(false)

    private val mutablePermissionRequestEffect = MutableEffectSharedFlow<Unit>()

    private var isSaveWallpaperRequested = false

    override val openSetWallpaperTargetDialogStateFlow: StateFlow<Boolean> =
        mutableSetWallpaperTargetDialogSource.asStateFlow()

    override val saveWallpaperEffect: SharedFlow<Unit> = mutableSaveWallpaperEffect.asSharedFlow()

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

    override val openPermissionRequestRationaleDialogStateFlow: StateFlow<Boolean> =
        mutableOpenPermissionRequestRationaleDialogSource.asStateFlow()

    override val permissionRequestEffect: SharedFlow<Unit> =
        mutablePermissionRequestEffect.asSharedFlow()

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

<<<<<<< HEAD
    override fun onClickedSaveWallpaper(
        canSkipPermissionRequest: Boolean,
        isPermissionRequestGrant: Boolean,
        shouldShowPermissionRequestRationale: Boolean,
    ) {
        viewModelScope.launch {
            isSaveWallpaperRequested = true
            when {
                canSkipPermissionRequest -> executeSaveWallpaperEffect()
                isPermissionRequestGrant -> executeSaveWallpaperEffect()
                shouldShowPermissionRequestRationale -> mutableOpenPermissionRequestRationaleDialogSource.value = true
                !isPermissionRequestGrant -> mutablePermissionRequestEffect.emit(Unit)
            }
        }
    }

    override fun onClickedConfirmPermission() {
        viewModelScope.launch {
            mutableOpenPermissionRequestRationaleDialogSource.value = false
            isSaveWallpaperRequested = true
            mutablePermissionRequestEffect.emit(Unit)
        }
    }

    override fun onPermissionRequestRationaleDialogDismissRequested() {
        mutableOpenPermissionRequestRationaleDialogSource.value = false
    }

    override fun onPermissionStateChanged(isGranted: Boolean) {
        viewModelScope.launch {
            mutableOpenPermissionRequestRationaleDialogSource.value = false
            if (isSaveWallpaperRequested && isGranted) {
                executeSaveWallpaperEffect()
            }
        }
    }

    private suspend fun executeSaveWallpaperEffect() {
        isSaveWallpaperRequested = false
        mutableSaveWallpaperEffect.emit(Unit)
=======
    override fun onSuccessSetWallPaper() {
        useCase.recycleWallpaper()
>>>>>>> main
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
