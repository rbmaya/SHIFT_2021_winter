package com.example.a0_task.domain

import com.example.a0_task.domain.city_model.City
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Test

class GetCityUseCaseTest {
    private val cityRepository: CityRepository = mockk()
    private val singleCity: Single<City> = mockk()
    private val getCityUseCase: GetCityUseCase = GetCityUseCase(cityRepository)

    @Test
    fun `invoke EXPECT city by name`(){
        every {cityRepository.getCity(String())} returns singleCity

        val cityTest = getCityUseCase(String())

        assertEquals(cityTest, singleCity)
    }
}