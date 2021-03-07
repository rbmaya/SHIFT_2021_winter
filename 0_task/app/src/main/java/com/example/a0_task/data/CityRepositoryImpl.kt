package com.example.a0_task.data

import com.example.a0_task.domain.city_model.City
import com.example.a0_task.domain.CityRepository
import com.example.a0_task.domain.city_model.Response
import io.reactivex.Single

class CityRepositoryImpl(private val cityDataSource: CityDataSource) : CityRepository {
    override fun getCity(name: String): Single<City> = cityDataSource.getCity(name)

    override fun getCities(): Single<Response> = cityDataSource.getCities()

}