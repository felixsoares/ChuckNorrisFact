package com.felix.chucknorrisfact.ui.main_feature.di

import com.felix.chucknorrisfact.core.data.remote.ApiService
import com.felix.chucknorrisfact.ui.main_feature.data.repository.FactRepositoryImpl
import com.felix.chucknorrisfact.ui.main_feature.data.source.FactRemoteDataSourceImpl
import com.felix.chucknorrisfact.ui.main_feature.domain.repository.FactRepository
import com.felix.chucknorrisfact.ui.main_feature.domain.source.FactRemoteDataSource
import com.felix.chucknorrisfact.ui.main_feature.domain.usecase.GetCategoriesUseCase
import com.felix.chucknorrisfact.ui.main_feature.domain.usecase.GetCategoriesUseCaseImpl
import com.felix.chucknorrisfact.ui.main_feature.domain.usecase.GetFactUseCase
import com.felix.chucknorrisfact.ui.main_feature.domain.usecase.GetFactUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainFeatureModule {

    @Provides
    @Singleton
    fun provideFactRemoteDataSource(
        apiService: ApiService
    ): FactRemoteDataSource = FactRemoteDataSourceImpl(apiService)

    @Provides
    @Singleton
    fun provideFactRepository(
        factRemoteDataSource: FactRemoteDataSource
    ): FactRepository = FactRepositoryImpl(factRemoteDataSource)

    @Provides
    @Singleton
    fun provideGetFactUseCase(
        factRepository: FactRepository
    ): GetFactUseCase = GetFactUseCaseImpl(factRepository)

    @Provides
    @Singleton
    fun provideGetCategoriesUseCase(
        factRepository: FactRepository
    ): GetCategoriesUseCase = GetCategoriesUseCaseImpl(factRepository)

}