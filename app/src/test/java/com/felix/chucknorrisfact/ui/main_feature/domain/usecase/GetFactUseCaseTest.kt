package com.felix.chucknorrisfact.ui.main_feature.domain.usecase

import com.felix.chucknorrisfact.core.domain.model.Fact
import com.felix.chucknorrisfact.core.util.ResultState
import com.felix.chucknorrisfact.ui.main_feature.domain.repository.FactRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetFactUseCaseTest {

    private lateinit var getFactUseCase: GetFactUseCase
    private val factRepository: FactRepository = mockk()

    @Before
    fun setUp() {
        getFactUseCase = GetFactUseCaseImpl(factRepository)
    }

    @Test
    fun `when invoke GetFactUseCase should return a fact`() = runTest {
        // Given
        val params = GetFactUseCase.Params("dev")
        val mock = mockFact()
        coEvery { factRepository.getFact(params.category) } returns ResultState.Success(mock)

        // When
        val result = getFactUseCase.invoke(params).first()

        // Then
        assertTrue(result is ResultState.Success)
        assertEquals(mock, (result as ResultState.Success).data)
    }

    @Test
    fun `when invoke GetFactUseCase should return an error`() = runTest {
        // Given
        val params = GetFactUseCase.Params("dev")
        val mockError = Exception("Error")
        coEvery { factRepository.getFact(params.category) } returns ResultState.Error(mockError)

        // When
        val result = getFactUseCase.invoke(params).first()

        // Then
        assertTrue(result is ResultState.Error)
        assertEquals(mockError, (result as ResultState.Error).exception)
    }

    private fun mockFact(): Fact {
        return Fact(
            id = "1",
            value = "value",
        )
    }
}