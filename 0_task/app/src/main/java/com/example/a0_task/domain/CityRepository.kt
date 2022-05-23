package com.example.a0_task.domain

import com.example.a0_task.domain.city_model.City
import kotlinx.coroutines.flow.Flow

interface CityRepository {
    fun getCity(name: String): Flow<City>

    fun getCities(): Flow<List<City>>
}