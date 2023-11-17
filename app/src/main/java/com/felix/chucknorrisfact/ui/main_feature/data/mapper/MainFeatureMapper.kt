package com.felix.chucknorrisfact.ui.main_feature.data.mapper

import com.felix.chucknorrisfact.core.data.remote.response.FactResponse
import com.felix.chucknorrisfact.core.domain.model.Category
import com.felix.chucknorrisfact.core.domain.model.Fact

fun FactResponse.toFact() = Fact(
    id = id,
    value = value,
)

fun List<String>.toCategories() = map { category ->
    Category(
        name = category
    )
}