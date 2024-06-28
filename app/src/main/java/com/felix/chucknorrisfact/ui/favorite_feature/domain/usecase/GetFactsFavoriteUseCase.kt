package com.felix.chucknorrisfact.ui.favorite_feature.domain.usecase

import com.felix.chucknorrisfact.core.domain.model.Fact
import com.felix.chucknorrisfact.ui.favorite_feature.domain.repository.FactFavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetFactsFavoriteUseCase {
    suspend fun invoke(): Flow<List<Fact>>
}

class GetFactsFavoriteUseCaseImpl @Inject constructor(
    private val repository: FactFavoriteRepository
) : GetFactsFavoriteUseCase {

    override suspend fun invoke(): Flow<List<Fact>> {
        return repository.getFacts()
    }

}