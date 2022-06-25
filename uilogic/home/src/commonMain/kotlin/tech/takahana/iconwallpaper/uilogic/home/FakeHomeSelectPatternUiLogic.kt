package tech.takahana.iconwallpaper.uilogic.home

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import tech.takahana.iconwallpaper.shared.assets.LocalImageAsset
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetId
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetName
import tech.takahana.iconwallpaper.shared.domain.domainobject.PatternType

class FakeHomeSelectPatternUiLogic : HomeSelectPatternUiLogic {
    private val dummyLocalImageAsset = LocalImageAsset(
        id = AssetId.requireGet("assetId1"), name = AssetName("assetName1")
    )
    private val onClickedPatternImpl: () -> Unit = { }

    override val patternTypeStateFlow: StateFlow<PatternType> = MutableStateFlow(PatternType.SMALL)

    override val selectedImageAssetStateFlow: StateFlow<ImageAssetUiModel> =
        MutableStateFlow(ImageAssetUiModel.Selectable(dummyLocalImageAsset, true))

    override fun onClickedPattern(patternType: PatternType) = onClickedPatternImpl()
}
