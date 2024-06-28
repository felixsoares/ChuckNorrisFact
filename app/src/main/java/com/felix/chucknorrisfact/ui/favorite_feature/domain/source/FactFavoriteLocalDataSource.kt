package com.felix.chucknorrisfact.ui.favorite_feature.domain.source

import com.felix.chucknorrisfact.core.domain.model.Fact
import kotlinx.coroutines.flow.Flow

interface FactFavoriteLocalDataSource {

    fun getFacts(): Flow<List<Fact>>

    suspend fun insert(fact: Fact)

    suspend fun delete(fact: Fact)

    suspend fun isFavorite(id: String): Boolean

}