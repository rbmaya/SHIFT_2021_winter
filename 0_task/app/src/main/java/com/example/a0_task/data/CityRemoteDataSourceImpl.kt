package com.example.a0_task.data

import com.example.a0_task.domain.city_model.City
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRemoteDataSourceImpl @Inject constructor(
    private val api: CityApi
) : CityDataSource {

    override suspend fun getCity(name: String): City = api.getCity(name)

    override suspend fun getCities(): List<City> = api.getCitiesList().list

}