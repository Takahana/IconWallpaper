package tech.takahana.iconwallpapaer.uilogic.home

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import tech.takahana.iconwallpaper.uilogic.home.SetWallpaperTargetUiModel
import tech.takahana.iconwallpaper.usecase.FakeHomeConfirmUseCase
import tech.takahana.iconwallpaper.usecase.home.SetWallpaperTargetUseCaseModel
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class HomeConfirmUiLogicImplTest {

    private lateinit var fakeUseCase: FakeHomeConfirmUseCase

    @BeforeTest
    fun setUp() {
        fakeUseCase = FakeHomeConfirmUseCase()
    }

    @Test
    fun onClickedSetWallpaper() = runTest {
        val uiLogic = HomeConfirmUiLogicImpl(
            viewModelScope = this,
            useCase = fakeUseCase,
            setWallpaperTargetMapper = FakePlatformSetWallpaperTargetMapper()
        )
        var callCount = 0
        fakeUseCase.setWallpaperImpl = {
            callCount++
            Result.success(Unit)
        }

        uiLogic.openSetWallpaperTargetDialogStateFlow.test {

            awaitItem().also {
                assertFalse(it)
            }

            uiLogic.onClickedSetWallpaper()

            awaitItem().also {
                assertEquals(1, callCount)
                assertTrue(it)
            }

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun onSetWallpaperTargetDialogDismissRequested() = runTest {
        val uiLogic = HomeConfirmUiLogicImpl(
            viewModelScope = this,
            useCase = fakeUseCase,
            setWallpaperTargetMapper = FakePlatformSetWallpaperTargetMapper()
        )
        var callCount = 0
        fakeUseCase.cancelSetWallpaperImpl = {
            callCount++
            Result.success(Unit)
        }

        uiLogic.openSetWallpaperTargetDialogStateFlow.test {

            awaitItem().also {
                assertFalse(it)
            }

            // -------- ダイアログを開く -------- //

            uiLogic.onClickedSetWallpaper()

            awaitItem().also {
                assertTrue(it)
            }

            // -------- ダイアログを閉じる -------- //

            uiLogic.onSetWallpaperTargetDialogDismissRequested()

            awaitItem().also {
                assertEquals(1, callCount)
                assertFalse(it)
            }

            // -------- 後処理 -------- //

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun onClickedSetWallpaperTarget() = runTest {
        val uiLogic = HomeConfirmUiLogicImpl(
            viewModelScope = this,
            useCase = fakeUseCase,
            setWallpaperTargetMapper = FakePlatformSetWallpaperTargetMapper()
        )
        var callCount = 0
        val target = FakePlatformSetWallpaperTargetUiModel.Home
        fakeUseCase.apply {
            selectSetWallpaperTargetImpl = {
                callCount++
                Result.success(it)
            }
        }
        uiLogic.setWallpaperEffect.test {
            uiLogic.onClickedSetWallpaperTarget(target)

            awaitItem().also {
                assertEquals(1, callCount)
                assertEquals(target, it)
                assertFalse(uiLogic.openSetWallpaperTargetDialogStateFlow.value)
            }

            cancelAndIgnoreRemainingEvents()
        }
    }

    sealed class FakePlatformSetWallpaperTargetUiModel : SetWallpaperTargetUiModel {
        object Home : FakePlatformSetWallpaperTargetUiModel()
    }

    sealed class FakePlatformSetWallpaperTargetUseCaseModel : SetWallpaperTargetUseCaseModel {
        object Home : FakePlatformSetWallpaperTargetUseCaseModel()
    }

    class FakePlatformSetWallpaperTargetMapper :
        HomeConfirmUiLogicImpl.AbstractMapper<SetWallpaperTargetUiModel, SetWallpaperTargetUseCaseModel>() {

        override fun mapToUseCaseModel(uiModel: Any): FakePlatformSetWallpaperTargetUseCaseModel {
            require(uiModel is FakePlatformSetWallpaperTargetUiModel)
            return when (uiModel) {
                FakePlatformSetWallpaperTargetUiModel.Home -> FakePlatformSetWallpaperTargetUseCaseModel.Home
            }
        }

        override fun mapToUiModel(useCaseModel: Any): FakePlatformSetWallpaperTargetUiModel {
            require(useCaseModel is FakePlatformSetWallpaperTargetUseCaseModel)
            return when (useCaseModel) {
                FakePlatformSetWallpaperTargetUseCaseModel.Home -> FakePlatformSetWallpaperTargetUiModel.Home
            }
        }
    }
}