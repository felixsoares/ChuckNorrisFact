package com.felix.chucknorrisfact.ui.main_feature.data.repository

import com.felix.chucknorrisfact.ui.main_feature.domain.source.FactRemoteDataSource
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class FactRepositoryTest {

    private lateinit var factRepository: FactRepositoryImpl
    private val factRemoteDataSource: FactRemoteDataSource = mockk(relaxed = true)

    @Before
    fun setUp() {
        factRepository = FactRepositoryImpl(factRemoteDataSource)
    }

    @Test
    fun `should call getFact from FactRemoteDataSource`() = runTest {
        // Given
        val category = "animal"

        // When
        factRepository.getFact(category)

        // Then
        coVerify { factRemoteDataSource.getFact(category) }
    }

    @Test
    fun `should call getCategories from FactRemoteDataSource`() = runTest {
        // When
        factRepository.getCategories()

        // Then
        coVerify { factRemoteDataSource.getCategories() }
    }
}