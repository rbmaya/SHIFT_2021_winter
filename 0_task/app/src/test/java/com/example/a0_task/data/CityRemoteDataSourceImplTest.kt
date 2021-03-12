package com.example.a0_task.data

import com.example.a0_task.domain.city_model.City
import com.example.a0_task.domain.city_model.Response
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Test

class CityRemoteDataSourceImplTest {
    private val api: CityApi = mockk()
    private val singleOfCity: Single<City> = mockk()
    private val singleOfResponse: Single<Response> = mockk()
    private val cityRemoteDataSourceImpl: CityRemoteDataSourceImpl = CityRemoteDataSourceImpl(api)

    @Test
    fun `get city EXPECT single city`(){
        every {api.getCity(String())} returns singleOfCity

        every {singleOfCity.subscribeOn(Schedulers.io())} returns singleOfCity

        val cityTest = cityRemoteDataSourceImpl.getCity(String())

        assertEquals(cityTest, singleOfCity)
    }

    @Test
    fun `get cities EXPECT single of cities list`(){
        every {api.getCitiesList()} returns singleOfResponse

        every {singleOfResponse.subscribeOn(Schedulers.io())} returns singleOfResponse

        val responseTest = cityRemoteDataSourceImpl.getCities()

        assertEquals(responseTest, singleOfResponse)
    }
}