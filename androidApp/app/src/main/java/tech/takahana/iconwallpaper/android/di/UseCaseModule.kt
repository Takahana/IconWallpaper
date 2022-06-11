package tech.takahana.iconwallpaper.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import tech.takahana.iconwallpaper.repository.asset.LocalImageAssetRepository
import tech.takahana.iconwallpaper.repository.asset.SelectImageAssetRepository
import tech.takahana.iconwallpaper.repository.asset.SelectPatternTypeRepository
import tech.takahana.iconwallpaper.usecase.home.HomeConfirmUseCase
import tech.takahana.iconwallpaper.usecase.home.HomeConfirmUseCaseImpl
import tech.takahana.iconwallpaper.usecase.home.HomeSelectImageAssetUseCase
import tech.takahana.iconwallpaper.usecase.home.HomeSelectImageAssetUseCaseImpl
import tech.takahana.iconwallpaper.usecase.home.HomeSelectPatternUseCase
import tech.takahana.iconwallpaper.usecase.home.HomeSelectPatternUseCaseImpl

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

    @Provides
    fun provideHomeSelectPatternScreenUseCase(
        selectPatternTypeRepository: SelectPatternTypeRepository,
        selectImageAssetRepository: SelectImageAssetRepository
    ): HomeSelectPatternUseCase {
        return HomeSelectPatternUseCaseImpl(
            selectPatternTypeRepository,
            selectImageAssetRepository
        )
    }

    @Provides
    fun provideHomeConfirmUseCase(): HomeConfirmUseCase {
        return HomeConfirmUseCaseImpl()
    }
}
