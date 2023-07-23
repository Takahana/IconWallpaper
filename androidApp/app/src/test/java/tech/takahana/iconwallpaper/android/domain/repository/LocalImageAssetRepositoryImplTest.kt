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
            ),
            LocalImageAsset(
                id = AssetId(R.drawable.bird.toString()),
                name = AssetName("bird"),
                resId = R.drawable.bird
            ),
            LocalImageAsset(
                id = AssetId(R.drawable.dog.toString()),
                name = AssetName("dog"),
                resId = R.drawable.dog
            ),
            LocalImageAsset(
                id = AssetId(R.drawable.elephantidae.toString()),
                name = AssetName("elephantidae"),
                resId = R.drawable.elephantidae
            ),
            LocalImageAsset(
                id = AssetId(R.drawable.hawks.toString()),
                name = AssetName("hawks"),
                resId = R.drawable.hawks
            ),
            LocalImageAsset(
                id = AssetId(R.drawable.hedgehog.toString()),
                name = AssetName("hedgedog"),
                resId = R.drawable.hedgehog
            ),
            LocalImageAsset(
                id = AssetId(R.drawable.penguin.toString()),
                name = AssetName("penguin"),
                resId = R.drawable.penguin
            ),
            LocalImageAsset(
                id = AssetId(R.drawable.rabbit.toString()),
                name = AssetName("rabbit"),
                resId = R.drawable.rabbit
            ),
            LocalImageAsset(
                id = AssetId(R.drawable.rat.toString()),
                name = AssetName("rat"),
                resId = R.drawable.rat
            ),
            LocalImageAsset(
                id = AssetId(R.drawable.shark.toString()),
                name = AssetName("shark"),
                resId = R.drawable.shark
            ),
            LocalImageAsset(
                id = AssetId(R.drawable.snowman.toString()),
                name = AssetName("snowman"),
                resId = R.drawable.snowman
            ),
            LocalImageAsset(
                id = AssetId(R.drawable.sunflower.toString()),
                name = AssetName("sunflower"),
                resId = R.drawable.sunflower
            )
        )

        val actual = repository.getAll()

        assertThat(actual, IsEqual(expected))
    }
}
