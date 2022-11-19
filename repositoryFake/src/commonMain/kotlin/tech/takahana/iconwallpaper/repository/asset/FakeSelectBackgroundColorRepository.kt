package tech.takahana.iconwallpaper.repository.asset

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import tech.takahana.iconwallpaper.repository.asset.SelectBackgroundColorRepository
import tech.takahana.iconwallpaper.shared.domain.domainobject.ColorType

class FakeSelectBackgroundColorRepository : SelectBackgroundColorRepository {

    var selectBackgroundColorFlowImpl: MutableStateFlow<ColorType> =
        MutableStateFlow(ColorType.Blue)

    override val selectBackgroundColorFlow: Flow<ColorType> =
        selectBackgroundColorFlowImpl.asStateFlow()

    override suspend fun setSelectedBackgroundColor(backgroundColor: ColorType) {
        selectBackgroundColorFlowImpl.value = backgroundColor
    }
}