package com.example.a0_task.presentation.details

import com.example.a0_task.data.CityLocalDataSourceImpl
import com.example.a0_task.data.CityRepositoryImpl
import com.example.a0_task.domain.GetCityUseCase

object DetailsPresenterFactory {
    fun getDetailsPresenter(id: Long): DetailPresenter{
        val cityDataSource = CityLocalDataSourceImpl()
        val cityRepository = CityRepositoryImpl(cityDataSource)
        val getCityUseCase = GetCityUseCase(cityRepository)
        return DetailPresenter(
            getCityUseCase = getCityUseCase,
            id = id
        )
    }
}