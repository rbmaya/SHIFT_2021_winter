package com.example.a0_task.domain

class GetCityUseCase(private val cityRepository: CityRepository) {
    operator fun invoke(id: Long): City? = cityRepository.getCity(id)
}