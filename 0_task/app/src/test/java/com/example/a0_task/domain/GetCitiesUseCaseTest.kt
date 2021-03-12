package com.example.a0_task.domain

import com.example.a0_task.domain.city_model.Response
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Test

class GetCitiesUseCaseTest {
    private val cityRepository: CityRepository = mockk()
    private val singleResponse: Single<Response> = mockk()
    private val getCitiesUseCase: GetCitiesUseCase = GetCitiesUseCase(cityRepository)

    @Test
    fun `invoke EXPECT list of cities`(){
        every {cityRepository.getCities()} returns singleResponse

        val citiesTest = getCitiesUseCase()

        assertEquals(citiesTest, singleResponse)
    }


}