package com.felix.chucknorrisfact.ui.main_feature.domain.usecase

import com.felix.chucknorrisfact.core.domain.model.Fact
import com.felix.chucknorrisfact.ui.favorite_feature.domain.repository.FactFavoriteRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SaveFactsFavoriteUseCaseTest {

    private lateinit var saveFactsFavoriteUseCase: SaveFactsFavoriteUseCase
    private val factRepository: FactFavoriteRepository = mockk(relaxed = true)

    @Before
    fun setUp() {
        saveFactsFavoriteUseCase = SaveFactsFavoriteUseCaseImpl(factRepository)
    }

    @Test
    fun `should call insert method of FactFavoriteRepository`() = runTest {
        // Given
        val fact = Fact(
            id = "1",
            value = "value"
        )

        // When
        saveFactsFavoriteUseCase.invoke(fact)

        // Then
        coVerify { factRepository.insert(fact) }
    }
}