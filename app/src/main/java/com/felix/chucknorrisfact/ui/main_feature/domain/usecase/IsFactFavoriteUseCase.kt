package com.felix.chucknorrisfact.ui.main_feature.domain.usecase

import com.felix.chucknorrisfact.ui.favorite_feature.domain.repository.FactFavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface IsFactFavoriteUseCase {
    suspend fun invoke(id: String): Flow<Boolean>
}

class IsFactFavoriteUseCaseImpl @Inject constructor(
    private val factRepository: FactFavoriteRepository
) : IsFactFavoriteUseCase {

    override suspend fun invoke(id: String): Flow<Boolean> {
        return flow {
            val isFavorite = factRepository.isFavorite(id)
            emit(isFavorite)
        }.flowOn(Dispatchers.IO)
    }
}