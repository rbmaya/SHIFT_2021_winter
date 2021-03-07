package com.example.a0_task.data

import com.example.a0_task.domain.city_model.City
import com.example.a0_task.domain.city_model.Response
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApi {
    @GET("find")
    fun getCitiesList(
        @Query("lat") lat: Int = 55,
        @Query("lon") lon: Int = 83,
        @Query("cnt") cnt: Int = 10,
        @Query("appid") appid: String = "c421cb993c62461b09eaad71baeab383"
    ): Single<Response>

    @GET("weather")
    fun getCity(
        @Query("q") city_name: String,
        @Query("appid") appid: String = "c421cb993c62461b09eaad71baeab383"
    ): Single<City>
}