package com.felix.chucknorrisfact.ui.favorite_feature.di

import com.felix.chucknorrisfact.core.data.local.dao.FactDao
import com.felix.chucknorrisfact.ui.favorite_feature.data.repository.FactFavoriteRepositoryImpl
import com.felix.chucknorrisfact.ui.favorite_feature.data.source.FactFavoriteLocalDataSourceImpl
import com.felix.chucknorrisfact.ui.favorite_feature.domain.repository.FactFavoriteRepository
import com.felix.chucknorrisfact.ui.favorite_feature.domain.source.FactFavoriteLocalDataSource
import com.felix.chucknorrisfact.ui.favorite_feature.domain.usecase.DeleteFactFavoriteUseCase
import com.felix.chucknorrisfact.ui.favorite_feature.domain.usecase.DeleteFactFavoriteUseCaseImpl
import com.felix.chucknorrisfact.ui.favorite_feature.domain.usecase.GetFactsFavoriteUseCase
import com.felix.chucknorrisfact.ui.favorite_feature.domain.usecase.GetFactsFavoriteUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteFeatureModule {

    @Provides
    @Singleton
    fun providesFavoriteLocalDataSource(
        factDao: FactDao
    ): FactFavoriteLocalDataSource = FactFavoriteLocalDataSourceImpl(factDao)

    @Provides
    @Singleton
    fun providesFavoriteRepository(
        factFavoriteLocalDataSource: FactFavoriteLocalDataSource
    ): FactFavoriteRepository = FactFavoriteRepositoryImpl(factFavoriteLocalDataSource)

    @Provides
    @Singleton
    fun providesGetFactsFavoriteUseCase(
        factFavoriteRepository: FactFavoriteRepository
    ): GetFactsFavoriteUseCase = GetFactsFavoriteUseCaseImpl(factFavoriteRepository)

    @Provides
    @Singleton
    fun providesDeleteFactsFavoriteUseCase(
        factFavoriteRepository: FactFavoriteRepository
    ): DeleteFactFavoriteUseCase = DeleteFactFavoriteUseCaseImpl(factFavoriteRepository)
}