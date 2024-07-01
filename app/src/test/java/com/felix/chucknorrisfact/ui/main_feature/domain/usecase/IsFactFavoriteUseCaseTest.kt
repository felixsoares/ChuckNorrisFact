package com.felix.chucknorrisfact.ui.main_feature.domain.usecase

import com.felix.chucknorrisfact.ui.favorite_feature.domain.repository.FactFavoriteRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class IsFactFavoriteUseCaseTest {

    private lateinit var isFactFavoriteUseCase: IsFactFavoriteUseCase
    private val factRepository: FactFavoriteRepository = mockk()

    @Before
    fun setUp() {
        isFactFavoriteUseCase = IsFactFavoriteUseCaseImpl(factRepository)
    }

    @Test
    fun `should return true when have a favorite fact saved with this id`() = runTest {
        // Given
        val mockId = "1"
        val mockResult = true
        coEvery { factRepository.isFavorite(mockId) } returns mockResult

        // When
        val result = isFactFavoriteUseCase.invoke(mockId).first()

        // Then
        assertTrue(result)
    }

    @Test
    fun `should return false when don't have a favorite fact saved with this id`() = runTest {
        // Given
        val mockId = "1"
        val mockResult = false
        coEvery { factRepository.isFavorite(mockId) } returns mockResult

        // When
        val result = isFactFavoriteUseCase.invoke(mockId).first()

        // Then
        assertFalse(result)
    }

}