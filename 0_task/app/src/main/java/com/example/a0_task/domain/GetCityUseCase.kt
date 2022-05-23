package com.example.a0_task.domain

import com.example.a0_task.domain.city_model.City
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCityUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {
    operator fun invoke(name: String): Flow<City> = cityRepository.getCity(name)
}