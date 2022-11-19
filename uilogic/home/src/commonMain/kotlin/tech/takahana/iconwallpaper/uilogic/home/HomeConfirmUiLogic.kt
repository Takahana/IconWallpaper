package tech.takahana.iconwallpaper.uilogic.home

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import tech.takahana.iconwallpaper.shared.assets.LocalImageAsset
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetId
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetName
import tech.takahana.iconwallpaper.shared.domain.domainobject.ColorType
import tech.takahana.iconwallpaper.shared.domain.domainobject.PatternType

interface HomeConfirmUiLogic {

    val openSetWallpaperTargetDialogStateFlow: StateFlow<Boolean>

    val setWallpaperEffect: SharedFlow<SetWallpaperTargetUiModel>

    val patternTypeStateFlow: StateFlow<PatternType>

    val selectedImageAssetStateFlow: StateFlow<ImageAssetUiModel>

    val backgroundColorStateFlow: StateFlow<ColorType>

    fun onClickedSetWallpaper()

    fun onSetWallpaperTargetDialogDismissRequested()

    fun onClickedSetWallpaperTarget(
        target: SetWallpaperTargetUiModel
    )

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

    override val patternTypeStateFlow: StateFlow<PatternType> = MutableStateFlow(PatternType.MEDIUM)

    override val selectedImageAssetStateFlow: StateFlow<ImageAssetUiModel> = MutableStateFlow(
        ImageAssetUiModel.Selectable(
            imageAsset = LocalImageAsset(
                id = AssetId("assetId"),
                name = AssetName("assetName"),
            ),
            isSelected = true,
        )
    )

    override val backgroundColorStateFlow: StateFlow<ColorType> = MutableStateFlow(ColorType.Red)

    override fun onClickedSetWallpaper() {
        openSetWallpaperTargetDialogStateFlowImpl.value = true
    }

    override fun onSetWallpaperTargetDialogDismissRequested() {
        openSetWallpaperTargetDialogStateFlowImpl.value = false
    }

    override fun onClickedSetWallpaperTarget(target: SetWallpaperTargetUiModel) =
        onClickedSetWallpaperTargetImpl(target)
}
