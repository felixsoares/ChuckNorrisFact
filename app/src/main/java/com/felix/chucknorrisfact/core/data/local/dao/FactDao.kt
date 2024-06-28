package com.felix.chucknorrisfact.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.felix.chucknorrisfact.core.data.local.entity.FactEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FactDao {

    @Insert(onConflict = androidx.room.OnConflictStrategy.IGNORE)
    suspend fun save(factEntity: FactEntity)

    @Query("SELECT * FROM fact")
    fun getFacts(): Flow<List<FactEntity>>

    @Delete
    suspend fun delete(factEntity: FactEntity)

    @Query("SELECT * FROM fact WHERE id = :factId")
    suspend fun isFavorite(factId: String): FactEntity?
}