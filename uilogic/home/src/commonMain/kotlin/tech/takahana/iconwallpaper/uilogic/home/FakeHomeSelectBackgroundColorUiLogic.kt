package tech.takahana.iconwallpaper.uilogic.home

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import tech.takahana.iconwallpaper.shared.domain.domainobject.ColorType

class FakeHomeSelectBackgroundColorUiLogic : HomeSelectBackgroundColorUiLogic {
    var onClickedBackgroundColorImpl: () -> Unit = { }

    override val backgroundColorStateFlow: StateFlow<ColorType> =
        MutableStateFlow(ColorType.Other(0xffb2dfdb))

    override fun onClickedBackgroundColor(colorType: ColorType) = onClickedBackgroundColorImpl()
}
