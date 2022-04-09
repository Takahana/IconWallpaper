package tech.takahana.iconwallpaper.usecase.home

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import tech.takahana.iconwallpaper.repository.asset.SelectImageAssetRepository
import tech.takahana.iconwallpaper.repository.asset.SelectPatternTypeRepository
import tech.takahana.iconwallpaper.shared.domain.domainobject.PatternType
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class HomeSelectPatternUseCaseImplTest {

    @MockK
    lateinit var mockSelectPatternTypeRepository: SelectPatternTypeRepository

    @MockK
    lateinit var mockSelectImageAssetRepository: SelectImageAssetRepository

    @BeforeTest
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun selectPattern() = runTest {
        val mockPatternType: PatternType = mockk()
        every { mockSelectImageAssetRepository.selectedImageAssetFlow } returns flowOf(null)
        every { mockSelectPatternTypeRepository.selectedPatternTypeFlow } returns flowOf(PatternType.SMALL)
        coEvery { mockSelectImageAssetRepository.setSelectedImageAsset(any()) } returns Unit
        coEvery { mockSelectPatternTypeRepository.setSelectedPatternType(any()) } returns Unit
        val useCase = HomeSelectPatternUseCaseImpl(
            mockSelectPatternTypeRepository,
            mockSelectImageAssetRepository
        )

        useCase.selectPattern(mockPatternType)

        coVerify(exactly = 1) {
            mockSelectPatternTypeRepository.setSelectedPatternType(
                mockPatternType
            )
        }
    }
}
