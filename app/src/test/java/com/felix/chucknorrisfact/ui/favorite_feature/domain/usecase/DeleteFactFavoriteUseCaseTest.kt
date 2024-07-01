package com.felix.chucknorrisfact.ui.favorite_feature.domain.usecase

import com.felix.chucknorrisfact.core.domain.model.Fact
import com.felix.chucknorrisfact.ui.favorite_feature.domain.repository.FactFavoriteRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DeleteFactFavoriteUseCaseTest {

    private lateinit var deleteFactFavoriteUseCase: DeleteFactFavoriteUseCase
    private val factFavoriteRepository: FactFavoriteRepository = mockk(relaxed = true)

    @Before
    fun setUp() {
        deleteFactFavoriteUseCase = DeleteFactFavoriteUseCaseImpl(factFavoriteRepository)
    }

    @Test
    fun `should call delete from FactFavoriteRepository`() = runTest {
        // Given
        val fact = Fact("id", "description")

        // When
        deleteFactFavoriteUseCase.invoke(fact)

        // Then
        coVerify { factFavoriteRepository.delete(fact) }
    }
}