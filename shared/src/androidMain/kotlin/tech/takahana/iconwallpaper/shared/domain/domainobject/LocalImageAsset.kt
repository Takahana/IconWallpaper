package tech.takahana.iconwallpaper.shared.domain.domainobject

import androidx.annotation.DrawableRes

actual data class LocalImageAsset actual constructor(
    val name: AssetName
) : ImageAsset {

    @DrawableRes
    var resId: Int = -1
        private set

    constructor(name: AssetName, @DrawableRes resId: Int) : this(name) {
        this.resId = resId
    }
}
