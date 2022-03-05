package tech.takahana.iconwallpaper.usecase.home

import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset

data class SelectedImageAssetUseCaseModel(
    val asset: ImageAsset?
) {
    val isNone: Boolean = asset == null
}
