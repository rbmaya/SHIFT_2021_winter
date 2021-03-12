package com.example.a0_task.presentation.details

import com.example.a0_task.data.CityRemoteDataSourceImpl
import com.example.a0_task.data.CityRepositoryImpl
import com.example.a0_task.data.RetrofitHolder
import com.example.a0_task.domain.GetCityUseCase

object DetailsPresenterFactory {
    fun getDetailsPresenter(name: String): DetailsPresenter{
        val cityDataSource = CityRemoteDataSourceImpl(RetrofitHolder.cityApi)
        val cityRepository = CityRepositoryImpl(cityDataSource)
        val getCityUseCase = GetCityUseCase(cityRepository)
        return DetailsPresenter(
            getCityUseCase = getCityUseCase,
            name = name
        )
    }
}