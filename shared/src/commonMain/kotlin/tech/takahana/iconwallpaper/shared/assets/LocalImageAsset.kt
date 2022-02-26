package tech.takahana.iconwallpaper.shared.assets

import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetId
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetName
import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset

expect class LocalImageAsset(
    id: AssetId,
    name: AssetName,
) : ImageAsset
