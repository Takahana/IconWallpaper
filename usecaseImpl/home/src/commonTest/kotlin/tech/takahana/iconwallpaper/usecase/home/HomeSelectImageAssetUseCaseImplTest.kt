import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import tech.takahana.iconwallpaper.repository.asset.LocalImageAssetRepository
import tech.takahana.iconwallpaper.repository.asset.SelectImageAssetRepository
import tech.takahana.iconwallpaper.shared.assets.LocalImageAsset
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetId
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetName
import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset
import tech.takahana.iconwallpaper.usecase.home.HomeSelectImageAssetUseCaseImpl
import tech.takahana.iconwallpaper.usecase.home.ImageAssetUseCaseModel
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class HomeSelectImageAssetUseCaseImplTest {

    @MockK
    lateinit var mockLocalImageAssetRepository: LocalImageAssetRepository

    @MockK
    lateinit var mockSelectImageAssetRepository: SelectImageAssetRepository

    @BeforeTest
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun getImageAssetListFlow_notSelected() = runTest {
        val dummyImageAssetList = listOf(
            LocalImageAsset(
                id = AssetId.requireGet("assetId1"),
                name = AssetName("assetId1")
            ),
            LocalImageAsset(
                id = AssetId.requireGet("assetId2"),
                name = AssetName("assetId2")
            )
        )

        every { mockLocalImageAssetRepository.getAll() } returns
            dummyImageAssetList

        every { mockSelectImageAssetRepository.selectedImageAssetFlow } returns flowOf(null)
        val useCase = HomeSelectImageAssetUseCaseImpl(
            mockLocalImageAssetRepository,
            mockSelectImageAssetRepository
        )

        val actual = useCase.imageAssetListFlow.first()

        actual.forEachIndexed { index, imageAssetUseCaseModel ->
            assertEquals(
                ImageAssetUseCaseModel.HasAsset(
                    asset = dummyImageAssetList[index],
                    isSelected = false
                ),
                imageAssetUseCaseModel
            )
        }

        verify(exactly = 1) { mockSelectImageAssetRepository.selectedImageAssetFlow }
        verify(exactly = 1) { mockLocalImageAssetRepository.getAll() }
    }

    @Test
    fun getImageAssetListFlow_isNotNone() = runTest {
        val dummyImageAssetList: List<DummyImageAsset> = listOf(
            DummyImageAsset(
                localImageAsset = LocalImageAsset(
                    id = AssetId.requireGet("assetId1"),
                    name = AssetName("assetId1")
                ),
                isSelected = true
            ),
            DummyImageAsset(
                localImageAsset = LocalImageAsset(
                    id = AssetId.requireGet("assetId2"),
                    name = AssetName("assetId2")
                ),
                isSelected = false
            )
        )

        every { mockLocalImageAssetRepository.getAll() } returns
            dummyImageAssetList.map {
                it.localImageAsset
            }

        every { mockSelectImageAssetRepository.selectedImageAssetFlow } returns flowOf(
            dummyImageAssetList[0].localImageAsset
        )
        val useCase = HomeSelectImageAssetUseCaseImpl(
            mockLocalImageAssetRepository,
            mockSelectImageAssetRepository
        )

        val actual = useCase.imageAssetListFlow.first()

        actual.forEachIndexed { index, imageAssetUseCaseModel ->
            assertEquals(
                ImageAssetUseCaseModel.HasAsset(
                    asset = dummyImageAssetList[index].localImageAsset,
                    isSelected = dummyImageAssetList[index].isSelected
                ),
                imageAssetUseCaseModel
            )
        }

        verify(exactly = 1) { mockSelectImageAssetRepository.selectedImageAssetFlow }
        verify(exactly = 1) { mockLocalImageAssetRepository.getAll() }
    }

    @Test
    fun selectImageAsset() = runTest {
        val mockImageAsset: ImageAsset = mockk()
        every { mockSelectImageAssetRepository.selectedImageAssetFlow } returns flowOf(null)
        every { mockLocalImageAssetRepository.getAll() } returns mockk()
        coEvery { mockSelectImageAssetRepository.setSelectedImageAsset(any()) } returns Unit
        val useCase = HomeSelectImageAssetUseCaseImpl(
            mockLocalImageAssetRepository,
            mockSelectImageAssetRepository
        )

        useCase.selectImageAsset(mockImageAsset)

        coVerify(exactly = 1) { mockSelectImageAssetRepository.setSelectedImageAsset(mockImageAsset) }
    }

    @Test
    fun unselectImageAsset() = runTest {
        every { mockSelectImageAssetRepository.selectedImageAssetFlow } returns flowOf(null)
        every { mockLocalImageAssetRepository.getAll() } returns mockk()
        coEvery { mockSelectImageAssetRepository.clearSelectedImageAsset() } returns Unit
        val useCase = HomeSelectImageAssetUseCaseImpl(
            mockLocalImageAssetRepository,
            mockSelectImageAssetRepository
        )

        useCase.unselectImageAsset()

        coVerify(exactly = 1) { mockSelectImageAssetRepository.clearSelectedImageAsset() }
    }

    data class DummyImageAsset(
        val localImageAsset: LocalImageAsset,
        val isSelected: Boolean
    )
}
