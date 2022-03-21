package tech.takahana.iconwallpaper.uilogic.home

import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset

sealed class ImageAssetUiModel {
    data class Selectable(
        val imageAsset: ImageAsset,
        val isSelected: Boolean
    ) : ImageAssetUiModel()

    object None : ImageAssetUiModel()
}
