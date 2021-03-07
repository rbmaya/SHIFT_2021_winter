package com.example.a0_task.domain

import com.example.a0_task.domain.city_model.Response
import io.reactivex.Single

class GetCitiesUseCase(private val cityRepository: CityRepository) {
    operator fun invoke(): Single<Response> = cityRepository.getCities()
}