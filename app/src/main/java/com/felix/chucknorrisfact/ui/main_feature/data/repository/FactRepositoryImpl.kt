package com.felix.chucknorrisfact.ui.main_feature.data.repository

import com.felix.chucknorrisfact.core.domain.model.Category
import com.felix.chucknorrisfact.core.domain.model.Fact
import com.felix.chucknorrisfact.core.util.ResultState
import com.felix.chucknorrisfact.ui.main_feature.domain.repository.FactRepository
import com.felix.chucknorrisfact.ui.main_feature.domain.source.FactRemoteDataSource
import javax.inject.Inject

class FactRepositoryImpl @Inject constructor(
    private val factRemoteDataSource: FactRemoteDataSource
) : FactRepository {

    override suspend fun getFact(category: String?): ResultState<Fact> {
        return factRemoteDataSource.getFact(category)
    }

    override suspend fun getCategories(): ResultState<List<Category>> {
        return factRemoteDataSource.getCategories()
    }
}