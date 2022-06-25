package tech.takahana.iconwallpaper.repositoryimpl.asset

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import tech.takahana.iconwallpaper.shared.domain.domainobject.ColorType
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class SelectBackgroundColorRepositoryImplTest {

    private lateinit var repository: SelectBackgroundColorRepositoryImpl

    @BeforeTest
    fun setUp() {
        repository = SelectBackgroundColorRepositoryImpl()
    }

    @Test
    fun setSelectedColorType() = runTest {
        val actual1 = repository.selectBackgroundColorFlow.first()

        assertEquals(ColorType.Other(0xffb2dfdb), actual1)

        val colorTypeRed = ColorType.Red

        repository.setSelectedBackgroundColor(colorTypeRed)
        val actual2 = repository.selectBackgroundColorFlow.first()

        assertEquals(colorTypeRed, actual2)
    }
}
