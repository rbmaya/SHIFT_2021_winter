package com.example.a0_task.domain

class GetCitiesUseCase(private val cityRepository: CityRepository) {
    operator fun invoke(): List<City> = cityRepository.getCities()
}