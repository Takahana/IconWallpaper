package tech.takahana.iconwallpaper.usecase.home

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tech.takahana.iconwallpaper.repository.asset.SelectPatternTypeRepository
import tech.takahana.iconwallpaper.shared.domain.domainobject.PatternType

class HomeSelectPatternUseCaseImpl(
    private val selectPatternTypeRepository: SelectPatternTypeRepository,
) : HomeSelectPatternUseCase {

    override val selectedPatternFlow: Flow<SelectedPatternUseCaseModel> =
        selectPatternTypeRepository.selectedPatternTypeFlow.map { patternType ->
            SelectedPatternUseCaseModel(patternType)
        }

    override suspend fun selectPattern(patternType: PatternType) {
        selectPatternTypeRepository.setSelectedPatternType(patternType)
    }
}
