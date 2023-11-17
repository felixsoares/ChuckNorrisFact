package com.felix.chucknorrisfact.ui.main_feature.domain.usecase

import com.felix.chucknorrisfact.core.domain.model.Fact
import com.felix.chucknorrisfact.core.util.ResultState
import com.felix.chucknorrisfact.ui.main_feature.domain.repository.FactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface GetFactUseCase {
    suspend operator fun invoke(params: Params): Flow<ResultState<Fact>>
    data class Params(
        val category: String? = null
    )
}

class GetFactUseCaseImpl @Inject constructor(
    private val repository: FactRepository
) : GetFactUseCase {
    override suspend fun invoke(params: GetFactUseCase.Params): Flow<ResultState<Fact>> {
        return flow {
            val fact = repository.getFact(params.category)
            emit(fact)
        }.flowOn(Dispatchers.IO)
    }

}