package com.example.a0_task.data

import com.example.a0_task.domain.City

interface CityDataSource {

    fun getCity(id: Long) : City?

    fun getCities(): List<City>
}