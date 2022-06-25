package tech.takahana.iconwallpaper.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.takahana.iconwallpaper.android.domain.repository.LocalImageAssetRepositoryImpl
import tech.takahana.iconwallpaper.repository.asset.LocalImageAssetRepository
import tech.takahana.iconwallpaper.repository.asset.SelectBackgroundColorRepository
import tech.takahana.iconwallpaper.repository.asset.SelectImageAssetRepository
import tech.takahana.iconwallpaper.repository.asset.SelectPatternTypeRepository
import tech.takahana.iconwallpaper.repositoryimpl.asset.SelectBackgroundColorRepositoryImpl
import tech.takahana.iconwallpaper.repositoryimpl.asset.SelectImageAssetRepositoryImpl
import tech.takahana.iconwallpaper.repositoryimpl.asset.SelectPatternTypeRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideSelectedImageAssetRepository(): SelectImageAssetRepository {
        return SelectImageAssetRepositoryImpl()
    }

    @Singleton
    @Provides
    fun provideLocalImageAssetRepository(): LocalImageAssetRepository {
        return LocalImageAssetRepositoryImpl()
    }

    @Singleton
    @Provides
    fun provideSelectPatternTypeRepository(): SelectPatternTypeRepository {
        return SelectPatternTypeRepositoryImpl()
    }

    @Singleton
    @Provides
    fun provideSelectBackgroundColorRepository(): SelectBackgroundColorRepository {
        return SelectBackgroundColorRepositoryImpl()
    }
}
