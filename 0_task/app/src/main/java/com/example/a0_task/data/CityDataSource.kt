package com.example.a0_task.data

import com.example.a0_task.domain.city_model.City
import com.example.a0_task.domain.city_model.Response
import io.reactivex.Single

interface CityDataSource {

    fun getCity(name: String) : Single<City>

    fun getCities(): Single<Response>
}