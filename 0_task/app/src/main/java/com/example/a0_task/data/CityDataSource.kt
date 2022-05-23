package com.example.a0_task.data

import com.example.a0_task.domain.city_model.City

interface CityDataSource {

    suspend fun getCity(name: String) : City

    suspend fun getCities(): List<City>
}