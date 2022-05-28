package tech.takahana.iconwallpaper.repositoryimpl.asset

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import tech.takahana.iconwallpaper.repository.asset.SelectBackgroundColorRepository
import tech.takahana.iconwallpaper.shared.domain.domainobject.ColorType

class SelectBackgroundColorRepositoryImpl : SelectBackgroundColorRepository {

    private val mutableSelectBackgroundColorSource =
        MutableStateFlow<ColorType>(ColorType.Other(0xffb2dfdb))

    override val selectBackgroundColorFlow: Flow<ColorType> =
        mutableSelectBackgroundColorSource.asStateFlow()

    override suspend fun setSelectedBackgroundColor(backgroundColor: ColorType) {
        mutableSelectBackgroundColorSource.value = backgroundColor
    }
}
