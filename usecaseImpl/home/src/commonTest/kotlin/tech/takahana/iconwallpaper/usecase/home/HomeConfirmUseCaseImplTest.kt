package tech.takahana.iconwallpaper.usecase.home

import tech.takahana.iconwallpaper.repository.asset.FakeSelectBackgroundColorRepository
import tech.takahana.iconwallpaper.repository.asset.FakeSelectImageAssetRepository
import tech.takahana.iconwallpaper.repository.asset.FakeSelectPatternTypeRepository
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
