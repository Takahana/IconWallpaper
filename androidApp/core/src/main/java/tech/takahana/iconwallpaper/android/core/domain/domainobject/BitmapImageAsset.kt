package tech.takahana.iconwallpaper.android.core.domain.domainobject

import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetId
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetName
import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset

data class BitmapImageAsset(
    override val id: AssetId,
    override val name: AssetName
) : ImageAsset
