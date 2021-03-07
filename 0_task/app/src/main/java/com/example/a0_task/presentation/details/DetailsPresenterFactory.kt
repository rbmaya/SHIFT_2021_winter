package com.example.a0_task.presentation.details

import com.example.a0_task.data.CityLocalDataSourceImpl
import com.example.a0_task.data.CityRepositoryImpl
import com.example.a0_task.data.RetrofitHolder
import com.example.a0_task.domain.GetCityUseCase

object DetailsPresenterFactory {
    fun getDetailsPresenter(name: String): DetailPresenter{
        val cityDataSource = CityLocalDataSourceImpl(RetrofitHolder.cityApi)
        val cityRepository = CityRepositoryImpl(cityDataSource)
        val getCityUseCase = GetCityUseCase(cityRepository)
        return DetailPresenter(
            getCityUseCase = getCityUseCase,
            name = name
        )
    }
}