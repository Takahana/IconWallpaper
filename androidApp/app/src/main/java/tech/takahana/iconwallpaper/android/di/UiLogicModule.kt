package tech.takahana.iconwallpaper.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import tech.takahana.iconwallpapaer.uilogic.home.HomeSelectImageAssetUiLogicImpl
import tech.takahana.iconwallpaper.uilogic.home.HomeSelectImageAssetUiLogic
import tech.takahana.iconwallpaper.usecase.home.HomeSelectImageAssetUseCase

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
}
