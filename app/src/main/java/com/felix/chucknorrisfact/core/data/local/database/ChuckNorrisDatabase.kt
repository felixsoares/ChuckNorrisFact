package com.felix.chucknorrisfact.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.felix.chucknorrisfact.core.data.local.dao.FactDao
import com.felix.chucknorrisfact.core.data.local.entity.FactEntity

@Database(
    entities = [
        FactEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class ChuckNorrisDatabase : RoomDatabase() {

    abstract fun factDao(): FactDao
}