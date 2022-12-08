package tech.takahana.iconwallpaper.repository.asset

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import tech.takahana.iconwallpaper.shared.domain.domainobject.PatternType

class FakeSelectPatternTypeRepository : SelectPatternTypeRepository {

    var selectedPatternTypeFlowImpl: MutableStateFlow<PatternType> =
        MutableStateFlow(PatternType.SMALL)

    override val selectedPatternTypeFlow: Flow<PatternType> =
        selectedPatternTypeFlowImpl.asStateFlow()

    override suspend fun setSelectedPatternType(patternType: PatternType) {
        selectedPatternTypeFlowImpl.value = patternType
    }
}
