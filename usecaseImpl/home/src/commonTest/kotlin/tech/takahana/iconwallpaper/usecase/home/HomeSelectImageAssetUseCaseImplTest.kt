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
import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset
import tech.takahana.iconwallpaper.usecase.home.HomeSelectImageAssetUseCaseImpl
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

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
    fun getSelectedImageAssetFlow_isNone() = runTest {
        every { mockSelectImageAssetRepository.selectedImageAssetFlow } returns flowOf(null)
        val useCase = HomeSelectImageAssetUseCaseImpl(
            mockLocalImageAssetRepository,
            mockSelectImageAssetRepository
        )

        val actual = useCase.imageAssetListFlow.first()

        verify(exactly = 1) { mockSelectImageAssetRepository.selectedImageAssetFlow }
        assertTrue { actual.isNone }
    }

    @Test
    fun getSelectedImageAssetFlow_isNotNone() = runTest {
        every { mockSelectImageAssetRepository.selectedImageAssetFlow } returns flowOf(mockk<ImageAsset>())
        val useCase = HomeSelectImageAssetUseCaseImpl(
            mockLocalImageAssetRepository,
            mockSelectImageAssetRepository
        )

        val actual = useCase.imageAssetListFlow.first()

        verify(exactly = 1) { mockSelectImageAssetRepository.selectedImageAssetFlow }
        assertFalse { actual.isNone }
    }

    @Test
    fun getAllImageAsset() = runTest {
        val mockLocalImageAssetList: List<LocalImageAsset> = mockk()
        every { mockLocalImageAssetRepository.getAll() } returns mockLocalImageAssetList
        every { mockSelectImageAssetRepository.selectedImageAssetFlow } returns flowOf(null)
        val useCase = HomeSelectImageAssetUseCaseImpl(
            mockLocalImageAssetRepository,
            mockSelectImageAssetRepository
        )

        val actual = useCase.getAllImageAsset()

        verify(exactly = 1) { mockLocalImageAssetRepository.getAll() }
        assertEquals(mockLocalImageAssetList, actual)
    }

    @Test
    fun selectImageAsset() = runTest {
        val mockImageAsset: ImageAsset = mockk()
        every { mockSelectImageAssetRepository.selectedImageAssetFlow } returns flowOf(null)
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
        coEvery { mockSelectImageAssetRepository.clearSelectedImageAsset() } returns Unit
        val useCase = HomeSelectImageAssetUseCaseImpl(
            mockLocalImageAssetRepository,
            mockSelectImageAssetRepository
        )

        useCase.unselectImageAsset()

        coVerify(exactly = 1) { mockSelectImageAssetRepository.clearSelectedImageAsset() }
    }
}
