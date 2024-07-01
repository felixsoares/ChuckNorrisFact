package com.felix.chucknorrisfact.ui.favorite_feature.domain.usecase

import com.felix.chucknorrisfact.core.domain.model.Fact
import com.felix.chucknorrisfact.ui.favorite_feature.domain.repository.FactFavoriteRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetFactsFavoriteUseCaseTest {

    private lateinit var getFactsFavoriteUseCase: GetFactsFavoriteUseCase
    private val factFavoriteRepository: FactFavoriteRepository = mockk(relaxed = true)

    @Before
    fun setUp() {
        getFactsFavoriteUseCase = GetFactsFavoriteUseCaseImpl(factFavoriteRepository)
    }

    @Test
    fun `should call getFacts from FactFavoriteRepository`() = runTest {
        // Given
        val facts = listOf(Fact("id", "description"), Fact("id2", "description2"))
        coEvery { factFavoriteRepository.getFacts() } returns flowOf(facts)

        // When
        val result = getFactsFavoriteUseCase.invoke().first()

        // Then
        assertEquals(facts, result)
        coVerify { factFavoriteRepository.getFacts() }
    }
}