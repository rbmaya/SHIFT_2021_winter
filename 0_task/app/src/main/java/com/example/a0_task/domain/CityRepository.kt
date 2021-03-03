package com.example.a0_task.domain

interface CityRepository {
    fun getCity(id: Long): City?

    fun getCities(): List<City>
}