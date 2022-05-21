package tech.takahana.iconwallpaper.repositoryimpl.asset

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import tech.takahana.iconwallpaper.repository.asset.SelectBackgroundColorRepository
import tech.takahana.iconwallpaper.shared.domain.domainobject.BackgroundColor

class SelectBackgroundColorRepositoryImpl : SelectBackgroundColorRepository {

    private val mutableSelectBackgroundColorSource =
        MutableStateFlow<BackgroundColor>(BackgroundColor.Other(0xffb2dfdb))

    override val selectBackgroundColorFlow: Flow<BackgroundColor> =
        mutableSelectBackgroundColorSource.asStateFlow()

    override suspend fun setSelectedBackgroundColor(backgroundColor: BackgroundColor) {
        mutableSelectBackgroundColorSource.value = backgroundColor
    }
}
