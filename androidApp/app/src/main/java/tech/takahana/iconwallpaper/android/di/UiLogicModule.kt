package tech.takahana.iconwallpaper.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import tech.takahana.iconwallpapaer.uilogic.home.HomeSelectImageAssetUiLogicImpl
import tech.takahana.iconwallpapaer.uilogic.home.HomeSelectPatternUiLogicImpl
import tech.takahana.iconwallpaper.uilogic.home.HomeSelectImageAssetUiLogic
import tech.takahana.iconwallpaper.uilogic.home.HomeSelectPatternUiLogic
import tech.takahana.iconwallpaper.usecase.home.HomeSelectImageAssetUseCase
import tech.takahana.iconwallpaper.usecase.home.HomeSelectPatternUseCase

@InstallIn(ViewModelComponent::class)
@Module
object UiLogicModule {

    @Provides
    fun provideHomeSelectImageAssetUiLogicFactory(
        homeSelectImageAssetUseCase: HomeSelectImageAssetUseCase
    ): HomeSelectImageAssetUiLogic.Factory {
        return HomeSelectImageAssetUiLogicImpl.Factory(
            homeSelectImageAssetUseCase
        )
    }

    @Provides
    fun provideHomePatternUiLogicFactory(
        homeSelectPatternUseCase: HomeSelectPatternUseCase
    ): HomeSelectPatternUiLogic.Factory {
        return HomeSelectPatternUiLogicImpl.Factory(
            homeSelectPatternUseCase
        )
    }
}
