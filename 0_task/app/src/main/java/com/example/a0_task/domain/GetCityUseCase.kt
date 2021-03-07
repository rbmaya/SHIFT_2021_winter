package com.example.a0_task.domain

import com.example.a0_task.domain.city_model.City
import io.reactivex.Single

class GetCityUseCase(private val cityRepository: CityRepository) {
    operator fun invoke(name: String): Single<City> = cityRepository.getCity(name)
}