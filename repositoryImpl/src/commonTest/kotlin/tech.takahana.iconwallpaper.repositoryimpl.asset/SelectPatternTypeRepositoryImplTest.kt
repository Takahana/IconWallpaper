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
        val patternType = PatternType.SMALL

        repository.setSelectedPatternType(patternType)
        val actual = repository.selectedPatternTypeFlow.first()

        assertEquals(patternType, actual)
    }
}
