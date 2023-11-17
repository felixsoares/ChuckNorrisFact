package com.felix.chucknorrisfact.ui.main_feature.domain.usecase

import com.felix.chucknorrisfact.core.domain.model.Category
import com.felix.chucknorrisfact.core.util.ResultState
import com.felix.chucknorrisfact.ui.main_feature.domain.repository.FactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface GetCategoriesUseCase {
    suspend operator fun invoke(): Flow<ResultState<List<Category>>>
}

class GetCategoriesUseCaseImpl @Inject constructor(
    private val repository: FactRepository
) : GetCategoriesUseCase {

    override suspend fun invoke(): Flow<ResultState<List<Category>>> {
        return flow {
            val categories = repository.getCategories()
            emit(categories)
        }.flowOn(Dispatchers.IO)
    }

}