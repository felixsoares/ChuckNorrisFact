package com.felix.chucknorrisfact.ui.main_feature.data.mapper

import com.felix.chucknorrisfact.core.data.remote.response.FactResponse
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MainFeatureMapperTest {

    @Test
    fun `should convert FactResponse to Fact`() {
        val factResponse = FactResponse(
            iconUrl = "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
            id = "1",
            url = "https://api.chucknorris.io/jokes/1",
            value = "Chuck Norris can divide by zero"
        )

        val fact = factResponse.toFact()
        assertEquals("1", fact.id)
        assertEquals("Chuck Norris can divide by zero", fact.value)
    }

    @Test
    fun `should convert List of String to List of Category`() {
        val categories = listOf("animal", "career", "celebrity", "dev")

        val categoriesList = categories.toCategories()
        assertEquals(4, categoriesList.size)
        assertEquals("animal", categoriesList[0].name)
        assertEquals("career", categoriesList[1].name)
        assertEquals("celebrity", categoriesList[2].name)
        assertEquals("dev", categoriesList[3].name)
    }
}