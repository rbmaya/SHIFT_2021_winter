package com.example.a0_task.presentation.list

import com.example.a0_task.data.CityLocalDataSourceImpl
import com.example.a0_task.data.CityRepositoryImpl
import com.example.a0_task.domain.GetCitiesUseCase

object ListPresenterFactory {
    fun getListPresenter(): ListPresenter{
        val cityDataSource = CityLocalDataSourceImpl()
        val cityRepository = CityRepositoryImpl(cityDataSource)
        val getCitiesUseCase = GetCitiesUseCase(cityRepository)
        return ListPresenter(getCitiesUseCase)
    }
}