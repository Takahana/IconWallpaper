package tech.takahana.iconwallpaper.usecase.home

import kotlin.test.Test
import kotlin.test.assertEquals

class HomeConfirmUseCaseImplTest {

    @Test
    fun saveWallpaper() {
        val useCase = HomeConfirmUseCaseImpl()

        val actual = useCase.saveWallpaper()

        assertEquals(Result.success(Unit), actual)
    }

    @Test
    fun setWallpaper() {
        val useCase = HomeConfirmUseCaseImpl()

        val actual = useCase.setWallpaper()

        assertEquals(Result.success(Unit), actual)
    }

    @Test
    fun cancelSetWallpaper() {
        val useCase = HomeConfirmUseCaseImpl()

        val actual = useCase.cancelSetWallpaper()

        assertEquals(Result.success(Unit), actual)
    }

    @Test
    fun selectSetWallpaperTarget() {
        val useCase = HomeConfirmUseCaseImpl()

        val target = FakePlatformSetWallpaperTargetUseCaseModel.Lock
        val actual = useCase.selectSetWallpaperTarget(target)

        assertEquals(Result.success(target), actual)
    }

    sealed class FakePlatformSetWallpaperTargetUseCaseModel : SetWallpaperTargetUseCaseModel {
        object Lock : FakePlatformSetWallpaperTargetUseCaseModel()
    }
}
