package com.example.a0_task.data

import com.example.a0_task.domain.city_model.City
import com.example.a0_task.domain.city_model.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface CityApi {
    companion object {
        const val APP_ID = "c421cb993c62461b09eaad71baeab383"
    }

    @GET("find")
    suspend fun getCitiesList(
        @Query("lat") lat: Int = 55,
        @Query("lon") lon: Int = 83,
        @Query("cnt") cnt: Int = 10,
        @Query("appid") appId: String = APP_ID
    ): Response

    @GET("weather")
    suspend fun getCity(
        @Query("q") city_name: String,
        @Query("appid") appId: String = APP_ID
    ): City
}