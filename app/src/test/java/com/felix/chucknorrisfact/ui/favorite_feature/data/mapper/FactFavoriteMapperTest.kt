package com.felix.chucknorrisfact.ui.favorite_feature.data.mapper

import com.felix.chucknorrisfact.core.data.local.entity.FactEntity
import com.felix.chucknorrisfact.core.domain.model.Fact
import org.junit.Assert.assertEquals
import org.junit.Test

class FactFavoriteMapperTest {

    @Test
    fun `should map FactEntity to Fact`() {
        // Given
        val fact = Fact("id", "value")

        // When
        val result = fact.toEntity()

        // Then
        assertEquals(fact.id, result.id)
        assertEquals(fact.value, result.fact)
    }

    @Test
    fun `should map List of FactEntity to List of Fact`() {
        // Given
        val factEntityList = listOf(FactEntity("id1", "value1"), FactEntity("id2", "value2"))

        // When
        val result = factEntityList.toFacts()

        // Then
        assertEquals(factEntityList.size, result.size)
        assertEquals(factEntityList[0].id, result[0].id)
        assertEquals(factEntityList[0].fact, result[0].value)
        assertEquals(factEntityList[1].id, result[1].id)
        assertEquals(factEntityList[1].fact, result[1].value)
    }
}