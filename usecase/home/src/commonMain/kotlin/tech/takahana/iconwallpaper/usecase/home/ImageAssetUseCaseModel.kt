package tech.takahana.iconwallpaper.usecase.home

import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset

sealed class ImageAssetUseCaseModel {
    data class HasAsset(
        val asset: ImageAsset,
        val isSelected: Boolean
    ) : ImageAssetUseCaseModel()
    object None : ImageAssetUseCaseModel()
}
