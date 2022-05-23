package com.example.a0_task.data

import com.example.a0_task.domain.city_model.City
import com.example.a0_task.domain.CityRepository
import com.example.a0_task.domain.city_model.Response
import io.reactivex.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepositoryImpl @Inject constructor(
    private val cityDataSource: CityDataSource
) : CityRepository {
    override fun getCity(name: String): Flow<City> = flow {
        emit(cityDataSource.getCity(name))
    }.flowOn(Dispatchers.IO)

    override fun getCities(): Flow<List<City>> = flow {
        emit(cityDataSource.getCities())
    }.flowOn(Dispatchers.IO)

}