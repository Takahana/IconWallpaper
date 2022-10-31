package tech.takahana.iconwallpaper.shared.assets

import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetId
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetName
import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset

actual class BitmapImageAsset actual constructor(
    override val id: AssetId,
    override val name: AssetName
) : ImageAsset
