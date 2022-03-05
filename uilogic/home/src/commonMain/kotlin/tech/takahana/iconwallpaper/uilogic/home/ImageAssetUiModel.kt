package tech.takahana.iconwallpaper.uilogic.home

import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset

data class ImageAssetUiModel(
    val imageAsset: ImageAsset,
    var isSelected: Boolean
)
