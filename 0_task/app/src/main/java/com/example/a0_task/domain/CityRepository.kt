package com.example.a0_task.domain

import com.example.a0_task.domain.city_model.City
import com.example.a0_task.domain.city_model.Response
import io.reactivex.Single

interface CityRepository {
    fun getCity(name: String): Single<City>

    fun getCities(): Single<Response>
}