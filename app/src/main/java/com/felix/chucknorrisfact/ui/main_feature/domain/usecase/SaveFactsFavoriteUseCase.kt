package com.felix.chucknorrisfact.ui.main_feature.domain.usecase

import com.felix.chucknorrisfact.core.domain.model.Fact
import com.felix.chucknorrisfact.ui.favorite_feature.domain.repository.FactFavoriteRepository
import javax.inject.Inject

interface SaveFactsFavoriteUseCase {
    suspend fun invoke(fact: Fact)
}

class SaveFactsFavoriteUseCaseImpl @Inject constructor(
    private val factRepository: FactFavoriteRepository
) : SaveFactsFavoriteUseCase {

    override suspend fun invoke(fact: Fact) {
        factRepository.insert(fact)
    }

}