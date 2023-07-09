package tech.takahana.iconwallpaper.android.domain.repository

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test
import tech.takahana.iconwallpaper.android.home.R
import tech.takahana.iconwallpaper.shared.assets.LocalImageAsset
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetId
import tech.takahana.iconwallpaper.shared.domain.domainobject.AssetName

class LocalImageAssetRepositoryImplTest {

    private lateinit var repository: LocalImageAssetRepositoryImpl

    @Before
    fun setUp() {
        repository = LocalImageAssetRepositoryImpl()
    }

    @Test
    fun getAll() {
        val expected = listOf(
            LocalImageAsset(
                id = AssetId(R.drawable.cat.toString()),
                name = AssetName("cat"),
                resId = R.drawable.cat
            )
        )

        val actual = repository.getAll()

        assertThat(actual, IsEqual(expected))
    }
}
