package com.example.a0_task.data

import com.example.a0_task.domain.city_model.City
import com.example.a0_task.domain.city_model.Response
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Test

class CityRepositoryImplTest {
    private val singleOfCity: Single<City> = mockk()
    private val singleOfResponse: Single<Response> = mockk()
    private val cityDataSource: CityDataSource = mockk()
    private val cityRepositoryImpl: CityRepositoryImpl = CityRepositoryImpl(cityDataSource)

    @Test
    fun `get city EXPECT single city`(){
        every {cityDataSource.getCity(String())} returns singleOfCity

        val testCity = cityRepositoryImpl.getCity(String())

        assertEquals(testCity, singleOfCity)
    }

    @Test
    fun `get cities EXPECT single list cities`(){
        every {cityDataSource.getCities()} returns singleOfResponse

        val testCities = cityRepositoryImpl.getCities()

        assertEquals(testCities, singleOfResponse)
    }
}