package com.felix.chucknorrisfact.ui.main_feature.domain.source

import com.felix.chucknorrisfact.core.domain.model.Category
import com.felix.chucknorrisfact.core.domain.model.Fact
import com.felix.chucknorrisfact.core.util.ResultState

interface FactRemoteDataSource {
    suspend fun getFact(category: String? = null): ResultState<Fact>
    suspend fun getCategories(): ResultState<List<Category>>
}