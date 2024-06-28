package com.felix.chucknorrisfact.ui.favorite_feature.data.source

import com.felix.chucknorrisfact.core.data.local.dao.FactDao
import com.felix.chucknorrisfact.core.domain.model.Fact
import com.felix.chucknorrisfact.ui.favorite_feature.data.mapper.toEntity
import com.felix.chucknorrisfact.ui.favorite_feature.data.mapper.toFacts
import com.felix.chucknorrisfact.ui.favorite_feature.domain.source.FactFavoriteLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FactFavoriteLocalDataSourceImpl @Inject constructor(
    private val factDao: FactDao
): FactFavoriteLocalDataSource {

    override fun getFacts(): Flow<List<Fact>> {
        return factDao.getFacts().map {
            it.toFacts()
        }
    }

    override suspend fun insert(fact: Fact) {
        factDao.save(fact.toEntity())
    }

    override suspend fun delete(fact: Fact) {
        factDao.delete(fact.toEntity())
    }

    override suspend fun isFavorite(id: String): Boolean {
        return factDao.isFavorite(id) != null
    }

}