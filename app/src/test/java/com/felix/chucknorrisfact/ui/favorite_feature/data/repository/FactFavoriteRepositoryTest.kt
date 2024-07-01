package com.felix.chucknorrisfact.ui.favorite_feature.data.repository

import com.felix.chucknorrisfact.core.domain.model.Fact
import com.felix.chucknorrisfact.ui.favorite_feature.domain.source.FactFavoriteLocalDataSource
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class FactFavoriteRepositoryTest {

    private lateinit var factFavoriteRepository: FactFavoriteRepositoryImpl
    private val localDataSource: FactFavoriteLocalDataSource = mockk(relaxed = true)

    @Before
    fun setUp() {
        factFavoriteRepository = FactFavoriteRepositoryImpl(localDataSource)
    }

    @Test
    fun `should call getFacts from FactFavoriteLocalDataSource`() = runTest {
        // When
        factFavoriteRepository.getFacts()

        // Then
        coVerify { localDataSource.getFacts() }
    }

    @Test
    fun `should call insert from FactFavoriteLocalDataSource`() = runTest {
        // Given
        val fact = Fact("1", "Chuck Norris")

        // When
        factFavoriteRepository.insert(fact)

        // Then
        coVerify { localDataSource.insert(fact) }
    }

    @Test
    fun `should call delete from FactFavoriteLocalDataSource`() = runTest {
        // Given
        val fact = Fact("1", "Chuck Norris")

        // When
        factFavoriteRepository.delete(fact)

        // Then
        coVerify { localDataSource.delete(fact) }
    }

    @Test
    fun `should call isFavorite from FactFavoriteLocalDataSource`() = runTest {
        // Given
        val id = "1"

        // When
        factFavoriteRepository.isFavorite(id)

        // Then
        coVerify { localDataSource.isFavorite(id) }
    }
}