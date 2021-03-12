package com.example.a0_task.data

import com.example.a0_task.domain.city_model.City
import com.example.a0_task.domain.city_model.Response
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class CityRemoteDataSourceImpl(private val api: CityApi): CityDataSource {

    override fun getCity(name: String): Single<City> = api.getCity(name)
        .subscribeOn(Schedulers.io())

    override fun getCities(): Single<Response> = api.getCitiesList()
        .subscribeOn(Schedulers.io())
}