package com.felix.chucknorrisfact.ui.favorite_feature.domain.usecase

import com.felix.chucknorrisfact.core.domain.model.Fact
import com.felix.chucknorrisfact.ui.favorite_feature.domain.repository.FactFavoriteRepository
import javax.inject.Inject

interface DeleteFactFavoriteUseCase {
    suspend fun invoke(fact: Fact)
}

class DeleteFactFavoriteUseCaseImpl @Inject constructor(
    private val factFavoriteRepository: FactFavoriteRepository
) : DeleteFactFavoriteUseCase {

    override suspend fun invoke(fact: Fact) {
        factFavoriteRepository.delete(fact)
    }
}