package tech.takahana.iconwallpaper.android.domain.repository

import tech.takahana.iconwallpaper.android.core.CoreResDrawable
import tech.takahana.iconwallpaper.android.home.R
import tech.takahana.iconwallpaper.repository.asset.LocalImageAssetRepository
import tech.takahana.iconwallpaper.shared.assets.LocalImageAsset
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetId
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetName

class LocalImageAssetRepositoryImpl : LocalImageAssetRepository {

    override fun getAll(): List<LocalImageAsset> = listOf(
        LocalImageAsset(
            id = AssetId(CoreResDrawable.cat.toString()),
            name = AssetName("cat"),
            resId = CoreResDrawable.cat
        )
    )
}
