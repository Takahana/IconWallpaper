package tech.takahana.iconwallpaper.shared.assets

import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetName
import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset

actual data class LocalImageAsset actual constructor(
    val name: AssetName
) : ImageAsset
