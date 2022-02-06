package tech.takahana.iconwallpaper.shared.assets

import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetName
import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset

expect class LocalImageAsset(
    name: AssetName
) : ImageAsset