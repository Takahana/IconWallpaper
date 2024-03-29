package tech.takahana.iconwallpapaer.uilogic.home

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetId
import tech.takahana.iconwallpaper.shared.domain.domainobject.dummy.DummyImageAsset
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
            listOf(
                ImageAssetUseCaseModel.HasAsset(asset = dummyImageAssetList[0], isSelected = false),
                ImageAssetUseCaseModel.HasAsset(asset = dummyImageAssetList[1], isSelected = false)
            )
        )
        val uiLogic = HomeSelectImageAssetUiLogicImpl(
            TestScope(UnconfinedTestDispatcher(testScheduler)),
            mockHomeSelectImageAssetUseCase
        )

        assertEquals(
            expected = listOf(
                ImageAssetUiModel.AssetSelectable(dummyImageAssetList[0], isSelected = false),
                ImageAssetUiModel.AssetSelectable(dummyImageAssetList[1], isSelected = false),
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
            listOf(
                ImageAssetUseCaseModel.HasAsset(asset = dummyImageAssetList[0], isSelected = true),
                ImageAssetUseCaseModel.HasAsset(asset = dummyImageAssetList[1], isSelected = false)
            )
        )
        val uiLogic = HomeSelectImageAssetUiLogicImpl(
            TestScope(UnconfinedTestDispatcher(testScheduler)),
            mockHomeSelectImageAssetUseCase
        )

        assertEquals(
            expected = listOf(
                ImageAssetUiModel.AssetSelectable(dummyImageAssetList[0], isSelected = true),
                ImageAssetUiModel.AssetSelectable(dummyImageAssetList[1], isSelected = false),
            ),
            actual = uiLogic.imageAssetListStateFlow.value
        )
    }

    @Test
    fun onClickedImageAsset_isSelected() = runTest {
        coEvery { mockHomeSelectImageAssetUseCase.imageAssetListFlow } returns flowOf(emptyList())
        val uiLogic = HomeSelectImageAssetUiLogicImpl(
            TestScope(UnconfinedTestDispatcher(testScheduler)),
            mockHomeSelectImageAssetUseCase
        )

        uiLogic.onClickedImageAsset(
            imageAsset = ImageAssetUiModel.AssetSelectable(
                imageAsset = DummyImageAsset(),
                isSelected = true
            )
        )

        coVerify(exactly = 1) { mockHomeSelectImageAssetUseCase.unselectImageAsset() }
        coVerify(exactly = 0) { mockHomeSelectImageAssetUseCase.selectImageAsset(any()) }
    }

    @Test
    fun onClickedImageAsset_isNotSelected() = runTest {
        coEvery { mockHomeSelectImageAssetUseCase.imageAssetListFlow } returns flowOf(emptyList())
        val uiLogic = HomeSelectImageAssetUiLogicImpl(
            TestScope(UnconfinedTestDispatcher(testScheduler)),
            mockHomeSelectImageAssetUseCase
        )
        val dummyImageAsset = DummyImageAsset()

        uiLogic.onClickedImageAsset(
            imageAsset = ImageAssetUiModel.AssetSelectable(
                imageAsset = dummyImageAsset,
                isSelected = false
            )
        )

        coVerify(exactly = 0) { mockHomeSelectImageAssetUseCase.unselectImageAsset() }
        coVerify(exactly = 1) { mockHomeSelectImageAssetUseCase.selectImageAsset(dummyImageAsset) }
    }
}
