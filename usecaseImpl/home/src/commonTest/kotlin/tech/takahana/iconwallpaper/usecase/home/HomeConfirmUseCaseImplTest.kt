package tech.takahana.iconwallpaper.usecase.home

import app.cash.turbine.test
import kotlinx.coroutines.test.runTest
import tech.takahana.iconwallpaper.repository.asset.FakeSelectBackgroundColorRepository
import tech.takahana.iconwallpaper.repository.asset.FakeSelectImageAssetRepository
import tech.takahana.iconwallpaper.repository.asset.FakeSelectPatternTypeRepository
import tech.takahana.iconwallpaper.shared.domain.domainobject.ColorType
import tech.takahana.iconwallpaper.shared.domain.domainobject.PatternType
import tech.takahana.iconwallpaper.shared.domain.domainobject.dummy.DummyImageAsset
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class HomeConfirmUseCaseImplTest {

    private lateinit var useCase: HomeConfirmUseCaseImpl
    private lateinit var fakeSelectPatternTypeRepository: FakeSelectPatternTypeRepository
    private lateinit var fakeSelectBackgroundColorRepository: FakeSelectBackgroundColorRepository
    private lateinit var fakeSelectImageAssetRepository: FakeSelectImageAssetRepository

    @BeforeTest
    fun setUp() {
        fakeSelectPatternTypeRepository = FakeSelectPatternTypeRepository()
        fakeSelectBackgroundColorRepository = FakeSelectBackgroundColorRepository()
        fakeSelectImageAssetRepository = FakeSelectImageAssetRepository()

        useCase = HomeConfirmUseCaseImpl(
            selectPatternTypeRepository = fakeSelectPatternTypeRepository,
            selectBackgroundColorRepository = fakeSelectBackgroundColorRepository,
            selectImageAssetRepository = fakeSelectImageAssetRepository,
        )
    }

    @Test
    fun selectedImageAssetFlow() = runTest {
        val imageAsset = DummyImageAsset()
        useCase.selectedImageAssetFlow.test {
            fakeSelectImageAssetRepository.selectedImageAssetFlowImpl.emit(imageAsset)

            assertEquals(
                ImageAssetUseCaseModel.HasAsset(asset = imageAsset, isSelected = true),
                awaitItem()
            )

            expectNoEvents()
        }
    }

    @Test
    fun selectedPatternFlow() = runTest {
        useCase.selectedPatternFlow.test {
            // 初期値
            assertEquals(awaitItem(), SelectedPatternUseCaseModel(PatternType.SMALL))

            // パターンの変更
            fakeSelectPatternTypeRepository.selectedPatternTypeFlowImpl.value = PatternType.MEDIUM

            assertEquals(awaitItem(), SelectedPatternUseCaseModel(PatternType.MEDIUM))
        }
    }

    @Test
    fun selectedBackgroundColorFlow() = runTest {
        useCase.selectedBackgroundColorFlow.test {
            // 初期値
            assertEquals(awaitItem(), SelectedBackgroundColorUseCaseModel(ColorType.Blue))

            // 背景色の変更
            fakeSelectBackgroundColorRepository.selectBackgroundColorFlowImpl.value = ColorType.Red

            assertEquals(awaitItem(), SelectedBackgroundColorUseCaseModel(ColorType.Red))
        }
    }

    @Test
    fun saveWallpaper() {
        val actual = useCase.saveWallpaper()

        assertEquals(Result.success(Unit), actual)
    }

    @Test
    fun setWallpaper() {
        val actual = useCase.setWallpaper()

        assertEquals(Result.success(Unit), actual)
    }

    @Test
    fun cancelSetWallpaper() {
        val actual = useCase.cancelSetWallpaper()

        assertEquals(Result.success(Unit), actual)
    }

    @Test
    fun selectSetWallpaperTarget() {
        val target = FakePlatformSetWallpaperTargetUseCaseModel.Lock
        val actual = useCase.selectSetWallpaperTarget(target)

        assertEquals(Result.success(target), actual)
    }

    sealed class FakePlatformSetWallpaperTargetUseCaseModel : SetWallpaperTargetUseCaseModel {
        object Lock : FakePlatformSetWallpaperTargetUseCaseModel()
    }
}
