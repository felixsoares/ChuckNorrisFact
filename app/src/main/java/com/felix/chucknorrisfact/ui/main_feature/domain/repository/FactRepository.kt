package com.felix.chucknorrisfact.ui.main_feature.domain.repository

import com.felix.chucknorrisfact.core.domain.model.Category
import com.felix.chucknorrisfact.core.domain.model.Fact
import com.felix.chucknorrisfact.core.util.ResultState

interface FactRepository {
    suspend fun getFact(category: String? = null): ResultState<Fact>
    suspend fun getCategories(): ResultState<List<Category>>
}