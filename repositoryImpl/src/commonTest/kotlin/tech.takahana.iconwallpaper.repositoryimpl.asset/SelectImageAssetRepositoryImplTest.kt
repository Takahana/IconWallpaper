package tech.takahana.iconwallpaper.repositoryimpl.asset

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import tech.takahana.iconwallpaper.shared.domain.domainobject.dummy.DummyImageAsset
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

@ExperimentalCoroutinesApi
class SelectImageAssetRepositoryImplTest {

    private lateinit var repository: SelectImageAssetRepositoryImpl

    @BeforeTest
    fun setUp() {
        repository = SelectImageAssetRepositoryImpl()
    }

    @Test
    fun setSelectedImageAsset() = runTest {
        val imageAsset = DummyImageAsset()

        repository.setSelectedImageAsset(imageAsset)
        val actual = repository.selectedImageAssetFlow.first()

        assertEquals(imageAsset, actual)
    }

    @Test
    fun clearSelectedImageAsset() = runTest {
        val imageAsset = DummyImageAsset()

        repository.setSelectedImageAsset(imageAsset)
        val actual1 = repository.selectedImageAssetFlow.first()

        assertEquals(imageAsset, actual1)

        repository.clearSelectedImageAsset()
        val actual2 = repository.selectedImageAssetFlow.first()

        assertNull(actual2)
    }
}
