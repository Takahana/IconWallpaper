package tech.takahana.iconwallpaper.uilogic.home

import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset

sealed class ImageAssetUiModel {
    data class AssetSelectable(
        val imageAsset: ImageAsset,
        val isSelected: Boolean
    ) : ImageAssetUiModel()

    object None : ImageAssetUiModel()

    companion object {
        fun List<ImageAssetUiModel>.hasSelectedImageAsset(): Boolean {
            return any { imageAsset ->
                (imageAsset as? Selectable)?.isSelected ?: false
            }
        }
    }
}
