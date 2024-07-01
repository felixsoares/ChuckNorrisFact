package com.felix.chucknorrisfact.ui.main_feature.data.source

import com.felix.chucknorrisfact.core.data.remote.ApiService
import com.felix.chucknorrisfact.core.data.remote.response.FactResponse
import com.felix.chucknorrisfact.core.util.ResultState
import com.felix.chucknorrisfact.ui.main_feature.data.mapper.toFact
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class FactRemoteDataSourceTest {

    private lateinit var factRemoteDataSource: FactRemoteDataSourceImpl
    private val apiService: ApiService = mockk()

    @Before
    fun setUp() {
        factRemoteDataSource = FactRemoteDataSourceImpl(apiService)
    }

    @Test
    fun `should get Fact with success response when category isn't null`() = runTest {
        // Given
        val category = "dev"
        val factResponse = mockFactResponse()
        coEvery { apiService.getRandomFact(category) } returns factResponse

        // When
        val result = factRemoteDataSource.getFact(category)

        // Then
        assert(result is ResultState.Success)
        assert((result as ResultState.Success).data == factResponse.toFact())
    }

    @Test
    fun `should get Fact with error response`() = runTest {
        // Given
        coEvery { apiService.getRandomFact(null) } throws Exception()

        // When
        val result = factRemoteDataSource.getFact(null)

        // Then
        assert(result is ResultState.Error)
    }

    @Test
    fun `should get Categories with success response`() = runTest {
        // Given
        val categories = listOf("dev", "animal")
        coEvery { apiService.getCategories() } returns categories

        // When
        val result = factRemoteDataSource.getCategories()

        // Then
        assert(result is ResultState.Success)
        assert((result as ResultState.Success).data.size == categories.size)
    }

    @Test
    fun `should get Categories with error response`() = runTest {
        // Given
        coEvery { apiService.getCategories() } throws Exception()

        // When
        val result = factRemoteDataSource.getCategories()

        // Then
        assert(result is ResultState.Error)
    }

    @Test
    fun `should filter categories with success response`() = runTest {
        // Given
        val categories = listOf("dev", "animal", "explicit", "religion")
        coEvery { apiService.getCategories() } returns categories

        // When
        val result = factRemoteDataSource.getCategories()

        // Then
        assertTrue(result is ResultState.Success)
        assertEquals(2, (result as ResultState.Success).data.size)
    }

    private fun mockFactResponse(): FactResponse {
        return FactResponse(
            iconUrl = "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
            id = "1",
            url = "https://api.chucknorris.io/jokes/1",
            value = "Chuck Norris can divide by zero."
        )
    }
}