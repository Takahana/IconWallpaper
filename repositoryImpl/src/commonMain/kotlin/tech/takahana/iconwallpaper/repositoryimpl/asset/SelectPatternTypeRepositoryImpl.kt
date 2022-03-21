package tech.takahana.iconwallpaper.repositoryimpl.asset

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import tech.takahana.iconwallpaper.repository.asset.SelectPatternTypeRepository
import tech.takahana.iconwallpaper.shared.domain.domainobject.PatternType

class SelectPatternTypeRepositoryImpl : SelectPatternTypeRepository {

    private val mutableSelectImageAssetSource = MutableStateFlow(PatternType.SMALL)

    override val selectedPatternTypeFlow: Flow<PatternType> =
        mutableSelectImageAssetSource.asStateFlow()

    override suspend fun setSelectedPatternType(patternType: PatternType) {
        mutableSelectImageAssetSource.value = patternType
    }
}
