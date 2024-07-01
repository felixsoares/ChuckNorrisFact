package com.felix.chucknorrisfact.ui.main_feature.domain.usecase

import com.felix.chucknorrisfact.core.domain.model.Category
import com.felix.chucknorrisfact.core.util.ResultState
import com.felix.chucknorrisfact.ui.main_feature.domain.repository.FactRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetCategoriesUseCaseTest {

    private lateinit var getCategoriesUseCase: GetCategoriesUseCase
    private val factRepository: FactRepository = mockk()

    @Before
    fun setUp() {
        getCategoriesUseCase = GetCategoriesUseCaseImpl(factRepository)
    }

    @Test
    fun `should return categories when invoke is called`() = runTest {
        // Given
        val mockCategories = listOf(Category("cat1"), Category("cat2"))
        coEvery { factRepository.getCategories() } returns ResultState.Success(mockCategories)

        // When
        val result = getCategoriesUseCase.invoke().first()

        // Then
        assertTrue(result is ResultState.Success)
        assertEquals(mockCategories, (result as ResultState.Success).data)
    }

    @Test
    fun `should return error when invoke is called and getCategories fails`() = runTest {
        // Given
        val mockError = Exception("Error")
        coEvery { factRepository.getCategories() } returns ResultState.Error(mockError)

        // When
        val result = getCategoriesUseCase.invoke().first()

        // Then
        assertTrue(result is ResultState.Error)
        assertEquals(mockError, (result as ResultState.Error).exception)
    }
}