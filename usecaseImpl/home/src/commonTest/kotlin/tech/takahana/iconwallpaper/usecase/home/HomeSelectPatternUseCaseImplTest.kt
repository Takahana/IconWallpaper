package tech.takahana.iconwallpaper.usecase.home

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import tech.takahana.iconwallpaper.repository.asset.SelectImageAssetRepository
import tech.takahana.iconwallpaper.repository.asset.SelectPatternTypeRepository
import tech.takahana.iconwallpaper.shared.assets.LocalImageAsset
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetId
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetName
import tech.takahana.iconwallpaper.shared.domain.domainobject.PatternType
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

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
        val dummyLocalImageAsset = LocalImageAsset(
            id = AssetId.requireGet("assetId1"),
            name = AssetName("assetName1")
        )
        every { mockSelectImageAssetRepository.selectedImageAssetFlow } returns flowOf(
            dummyLocalImageAsset
        )
        every { mockSelectPatternTypeRepository.selectedPatternTypeFlow } returns flowOf(PatternType.SMALL)
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

    @Test
    fun wrapFlow_inUseCaseModel() = runTest {
        val dummyLocalImageAsset = LocalImageAsset(
            id = AssetId.requireGet("assetId1"),
            name = AssetName("assetName1")
        )
        every { mockSelectPatternTypeRepository.selectedPatternTypeFlow } returns flowOf(PatternType.SMALL)
        every { mockSelectImageAssetRepository.selectedImageAssetFlow } returns flowOf(
            dummyLocalImageAsset
        )

        val useCase = HomeSelectPatternUseCaseImpl(
            mockSelectPatternTypeRepository,
            mockSelectImageAssetRepository
        )
        val expectedPatternType = SelectedPatternUseCaseModel(PatternType.SMALL)

        val expectedImageAsset = ImageAssetUseCaseModel.HasAsset(dummyLocalImageAsset, true)

        val actualPatternType = useCase.selectedPatternFlow.first()

        val actualImageAsset = useCase.selectedImageAssetFlow.first()

        assertEquals(expectedPatternType, actualPatternType)

        assertEquals(expectedImageAsset, actualImageAsset)
    }
}
