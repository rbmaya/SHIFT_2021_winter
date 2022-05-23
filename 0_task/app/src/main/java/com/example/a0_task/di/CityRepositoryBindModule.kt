package com.example.a0_task.di

import com.example.a0_task.data.CityDataSource
import com.example.a0_task.data.CityRemoteDataSourceImpl
import com.example.a0_task.data.CityRepositoryImpl
import com.example.a0_task.domain.CityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CityRepositoryBindModule {

    @Binds
    @Singleton
    @Suppress("FunctionName")
    fun bindCityDataSourceImpl_to_CityDataSource(
        cityRemoteDataSourceImpl: CityRemoteDataSourceImpl
    ): CityDataSource

    @Binds
    @Singleton
    @Suppress("FunctionName")
    fun bindCityRepositoryImpl_to_CityRepository(
        cityRepositoryImpl: CityRepositoryImpl
    ): CityRepository
}