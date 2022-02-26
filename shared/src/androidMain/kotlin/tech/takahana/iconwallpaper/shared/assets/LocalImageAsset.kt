package tech.takahana.iconwallpaper.shared.assets

import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetId
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetName
import tech.takahana.iconwallpaper.shared.domain.domainobject.ImageAsset

actual data class LocalImageAsset actual constructor(
    override val id: AssetId,
    override val name: AssetName,
) : ImageAsset {

    @DrawableRes
    var resId: Int = ResourcesCompat.ID_NULL
        private set

    constructor(id: AssetId, name: AssetName, @DrawableRes resId: Int) : this(id, name) {
        this.resId = resId
    }
}
