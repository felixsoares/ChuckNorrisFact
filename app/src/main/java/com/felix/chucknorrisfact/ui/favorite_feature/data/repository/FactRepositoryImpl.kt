package com.felix.chucknorrisfact.ui.favorite_feature.data.repository

import com.felix.chucknorrisfact.core.domain.model.Fact
import com.felix.chucknorrisfact.ui.favorite_feature.domain.repository.FactFavoriteRepository
import com.felix.chucknorrisfact.ui.favorite_feature.domain.source.FactFavoriteLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FactFavoriteRepositoryImpl @Inject constructor(
    private val localDataSource: FactFavoriteLocalDataSource
) : FactFavoriteRepository {

    override fun getFacts(): Flow<List<Fact>> {
        return localDataSource.getFacts()
    }

    override suspend fun insert(fact: Fact) {
        localDataSource.insert(fact)
    }

    override suspend fun delete(fact: Fact) {
        localDataSource.delete(fact)
    }

    override suspend fun isFavorite(id: String): Boolean {
        return localDataSource.isFavorite(id)
    }
}