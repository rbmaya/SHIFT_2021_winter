package com.example.a0_task.domain

import com.example.a0_task.domain.city_model.City
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCitiesUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {
    operator fun invoke(): Flow<List<City>> = cityRepository.getCities()
}