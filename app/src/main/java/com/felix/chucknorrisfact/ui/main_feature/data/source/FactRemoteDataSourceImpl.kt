package com.felix.chucknorrisfact.ui.main_feature.data.source

import com.felix.chucknorrisfact.core.data.remote.ApiService
import com.felix.chucknorrisfact.core.domain.model.Category
import com.felix.chucknorrisfact.core.domain.model.Fact
import com.felix.chucknorrisfact.core.util.ResultState
import com.felix.chucknorrisfact.ui.main_feature.data.mapper.toCategories
import com.felix.chucknorrisfact.ui.main_feature.data.mapper.toFact
import com.felix.chucknorrisfact.ui.main_feature.domain.source.FactRemoteDataSource
import javax.inject.Inject

class FactRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : FactRemoteDataSource {

    override suspend fun getFact(category: String?): ResultState<Fact> {
        return try {
            val response = apiService.getRandomFact(category)
            val fact = response.toFact()
            ResultState.Success(fact)
        } catch (e: Exception) {
            ResultState.Error(e)
        }
    }

    override suspend fun getCategories(): ResultState<List<Category>> {
        return try {
            val response = apiService.getCategories()
            val categories = response.toCategories()
            ResultState.Success(categories)
        } catch (e: Exception) {
            ResultState.Error(e)
        }
    }

}