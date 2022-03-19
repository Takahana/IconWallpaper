package tech.takahana.iconwallpaper.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import tech.takahana.iconwallpaper.repository.asset.LocalImageAssetRepository
import tech.takahana.iconwallpaper.repository.asset.SelectImageAssetRepository
import tech.takahana.iconwallpaper.usecase.home.HomeSelectImageAssetUseCase
import tech.takahana.iconwallpaper.usecase.home.HomeSelectImageAssetUseCaseImpl

@InstallIn(ViewModelComponent::class)
@Module
object UseCaseModule {

    @Provides
    fun provideHomeSelectImageAssetUseCase(
        localImageAssetRepository: LocalImageAssetRepository,
        selectImageAssetRepository: SelectImageAssetRepository,
    ): HomeSelectImageAssetUseCase {
        return HomeSelectImageAssetUseCaseImpl(
            localImageAssetRepository,
            selectImageAssetRepository
        )
    }
}
