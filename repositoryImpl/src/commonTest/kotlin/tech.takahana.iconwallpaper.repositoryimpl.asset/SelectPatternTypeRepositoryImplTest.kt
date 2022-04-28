package tech.takahana.iconwallpaper.repositoryimpl.asset

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import tech.takahana.iconwallpaper.shared.domain.domainobject.PatternType
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class SelectPatternTypeRepositoryImplTest {

    private lateinit var repository: SelectPatternTypeRepositoryImpl

    @BeforeTest
    fun setUp() {
        repository = SelectPatternTypeRepositoryImpl()
    }

    @Test
    fun setSelectedPatternType() = runTest {
        val actual1 = repository.selectedPatternTypeFlow.first()

        assertEquals(PatternType.SMALL, actual1)

        val patternTypeLarge = PatternType.LARGE

        repository.setSelectedPatternType(patternTypeLarge)
        val actual2 = repository.selectedPatternTypeFlow.first()

        assertEquals(patternTypeLarge, actual2)
    }
}
