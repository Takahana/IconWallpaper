package tech.takahana.iconwallpapaer.uilogic.home

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetId
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetName
import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset
import tech.takahana.iconwallpaper.uilogic.home.ImageAssetUiModel
import tech.takahana.iconwallpaper.usecase.home.HomeSelectImageAssetUseCase
import tech.takahana.iconwallpaper.usecase.home.ImageAssetUseCaseModel
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class HomeSelectImageAssetUiLogicImplTest {

    @MockK
    lateinit var mockHomeSelectImageAssetUseCase: HomeSelectImageAssetUseCase

    @BeforeTest
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun imageAssetListStateFlow_isNotSelected() = runTest {
        val dummyImageAssetList = listOf(
            DummyImageAsset(id = AssetId.requireGet("assetId1")),
            DummyImageAsset(id = AssetId.requireGet("assetId2")),
        )
        coEvery { mockHomeSelectImageAssetUseCase.imageAssetListFlow } returns flowOf(
            ImageAssetUseCaseModel(asset = null)
        )
        val uiLogic = HomeSelectImageAssetUiLogicImpl(
            TestScope(UnconfinedTestDispatcher(testScheduler)),
            mockHomeSelectImageAssetUseCase
        )
        uiLogic.mutableImageAssetListSource.value = dummyImageAssetList

        assertEquals(
            expected = listOf(
                ImageAssetUiModel(dummyImageAssetList[0], isSelected = false),
                ImageAssetUiModel(dummyImageAssetList[1], isSelected = false),
            ),
            actual = uiLogic.imageAssetListStateFlow.value
        )
    }

    @Test
    fun imageAssetListStateFlow_isSelected() = runTest {
        val dummyImageAssetList = listOf(
            DummyImageAsset(id = AssetId.requireGet("assetId1")),
            DummyImageAsset(id = AssetId.requireGet("assetId2")),
        )
        coEvery { mockHomeSelectImageAssetUseCase.imageAssetListFlow } returns flowOf(
            ImageAssetUseCaseModel(
                asset = DummyImageAsset(id = AssetId.requireGet("assetId1"))
            )
        )
        val uiLogic = HomeSelectImageAssetUiLogicImpl(
            TestScope(UnconfinedTestDispatcher(testScheduler)),
            mockHomeSelectImageAssetUseCase
        )
        uiLogic.mutableImageAssetListSource.value = dummyImageAssetList

        assertEquals(
            expected = listOf(
                ImageAssetUiModel(dummyImageAssetList[0], isSelected = true),
                ImageAssetUiModel(dummyImageAssetList[1], isSelected = false),
            ),
            actual = uiLogic.imageAssetListStateFlow.value
        )
    }

    @Test
    fun onCreatedScreen() = runTest {
        val dummyImageAssetList = listOf(
            DummyImageAsset(id = AssetId.requireGet("assetId1")),
            DummyImageAsset(id = AssetId.requireGet("assetId2")),
        )
        coEvery { mockHomeSelectImageAssetUseCase.getAllImageAsset() } returns dummyImageAssetList
        coEvery { mockHomeSelectImageAssetUseCase.imageAssetListFlow } returns flowOf(
            ImageAssetUseCaseModel(
                asset = DummyImageAsset()
            )
        )
        val uiLogic = HomeSelectImageAssetUiLogicImpl(
            TestScope(UnconfinedTestDispatcher(testScheduler)),
            mockHomeSelectImageAssetUseCase
        )

        uiLogic.onCreatedScreen()

        coVerify(exactly = 1) { mockHomeSelectImageAssetUseCase.getAllImageAsset() }
        assertEquals(dummyImageAssetList, uiLogic.mutableImageAssetListSource.value)
    }

    @Test
    fun onClickedImageAsset_isSelected() = runTest {
        coEvery { mockHomeSelectImageAssetUseCase.imageAssetListFlow } returns flowOf(mockk())
        val uiLogic = HomeSelectImageAssetUiLogicImpl(
            TestScope(UnconfinedTestDispatcher(testScheduler)),
            mockHomeSelectImageAssetUseCase
        )

        uiLogic.onClickedImageAsset(
            imageAsset = ImageAssetUiModel(
                imageAsset = DummyImageAsset(),
                isSelected = true
            )
        )

        coVerify(exactly = 1) { mockHomeSelectImageAssetUseCase.unselectImageAsset() }
        coVerify(exactly = 0) { mockHomeSelectImageAssetUseCase.selectImageAsset(any()) }
    }

    @Test
    fun onClickedImageAsset_isNotSelected() = runTest {
        coEvery { mockHomeSelectImageAssetUseCase.imageAssetListFlow } returns flowOf(mockk())
        val uiLogic = HomeSelectImageAssetUiLogicImpl(
            TestScope(UnconfinedTestDispatcher(testScheduler)),
            mockHomeSelectImageAssetUseCase
        )
        val dummyImageAsset = DummyImageAsset()

        uiLogic.onClickedImageAsset(
            imageAsset = ImageAssetUiModel(
                imageAsset = dummyImageAsset,
                isSelected = false
            )
        )

        coVerify(exactly = 0) { mockHomeSelectImageAssetUseCase.unselectImageAsset() }
        coVerify(exactly = 1) { mockHomeSelectImageAssetUseCase.selectImageAsset(dummyImageAsset) }
    }

    data class DummyImageAsset(
        override val id: AssetId = AssetId("assetId"),
        override val name: AssetName = AssetName("assetName")
    ) : ImageAsset
}
