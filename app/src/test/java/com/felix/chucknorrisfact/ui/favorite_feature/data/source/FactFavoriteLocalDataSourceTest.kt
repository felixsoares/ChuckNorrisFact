package com.felix.chucknorrisfact.ui.favorite_feature.data.source

import com.felix.chucknorrisfact.core.data.local.dao.FactDao
import com.felix.chucknorrisfact.core.data.local.entity.FactEntity
import com.felix.chucknorrisfact.core.domain.model.Fact
import com.felix.chucknorrisfact.ui.favorite_feature.data.mapper.toEntity
import com.felix.chucknorrisfact.ui.favorite_feature.data.mapper.toFacts
import com.felix.chucknorrisfact.ui.favorite_feature.domain.source.FactFavoriteLocalDataSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class FactFavoriteLocalDataSourceTest {

    private lateinit var factFavoriteLocalDataSource: FactFavoriteLocalDataSource
    private val factDao: FactDao = mockk(relaxed = true)

    @Before
    fun setUp() {
        factFavoriteLocalDataSource = FactFavoriteLocalDataSourceImpl(factDao)
    }

    @Test
    fun `should get Facts when have a favorite facts saved`() = runTest {
        // Given
        val mockFacts = listOf(FactEntity("id1", "fact1"), FactEntity("id2", "fact2"))
        coEvery { factDao.getFacts() } returns flowOf(mockFacts)

        // When
        val result = factFavoriteLocalDataSource.getFacts().first()

        // Then
        assertEquals(mockFacts.toFacts(), result)
    }

    @Test
    fun `should insert Fact when invoke insert`() = runTest {
        // Given
        val fact = Fact("id", "fact")

        // When
        factFavoriteLocalDataSource.insert(fact)

        // Then
        coEvery { factDao.save(fact.toEntity()) }
    }

    @Test
    fun `should delete Fact when invoke delete`() = runTest {
        // Given
        val fact = Fact("id", "fact")

        // When
        factFavoriteLocalDataSource.delete(fact)

        // Then
        coEvery { factDao.delete(fact.toEntity()) }
    }

    @Test
    fun `should return true when invoke isFavorite and have a favorite fact`() = runTest {
        // Given
        val id = "id"
        coEvery { factDao.isFavorite(id) } returns FactEntity(id, "fact")

        // When
        val result = factFavoriteLocalDataSource.isFavorite(id)

        // Then
        assertTrue(result)
    }

    @Test
    fun `should return false when invoke isFavorite and don't have a favorite fact`() = runTest {
        // Given
        val id = "id"
        coEvery { factDao.isFavorite(id) } returns null

        // When
        val result = factFavoriteLocalDataSource.isFavorite(id)

        // Then
        assertFalse(result)
    }
}