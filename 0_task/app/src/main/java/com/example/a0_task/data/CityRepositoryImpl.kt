package com.example.a0_task.data

import com.example.a0_task.domain.City
import com.example.a0_task.domain.CityRepository

class CityRepositoryImpl(private val cityDataSource: CityDataSource) : CityRepository {
    override fun getCity(id: Long): City? = cityDataSource.getCity(id)

    override fun getCities(): List<City> = cityDataSource.getCities()

}