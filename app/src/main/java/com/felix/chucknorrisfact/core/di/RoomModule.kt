package com.felix.chucknorrisfact.core.di

import android.content.Context
import androidx.room.Room
import com.felix.chucknorrisfact.core.data.local.database.ChuckNorrisDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    const val DATABASE_NAME = "chuck_norris_database"

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ChuckNorrisDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideFactDao(
        database: ChuckNorrisDatabase
    ) = database.factDao()

}