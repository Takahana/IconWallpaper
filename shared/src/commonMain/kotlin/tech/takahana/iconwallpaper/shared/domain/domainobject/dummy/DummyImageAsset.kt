package tech.takahana.iconwallpaper.shared.domain.domainobject.dummy

import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetId
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetName
import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset

data class DummyImageAsset(
    override val id: AssetId = AssetId("assetId"),
    override val name: AssetName = AssetName("assetName")
) : ImageAsset