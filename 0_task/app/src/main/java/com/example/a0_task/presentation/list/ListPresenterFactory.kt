package com.example.a0_task.presentation.list

import com.example.a0_task.data.CityRemoteDataSourceImpl
import com.example.a0_task.data.CityRepositoryImpl
import com.example.a0_task.data.RetrofitHolder
import com.example.a0_task.domain.GetCitiesUseCase

object ListPresenterFactory {
    fun getListPresenter(): ListPresenter{
        val cityDataSource = CityRemoteDataSourceImpl(RetrofitHolder.cityApi)
        val cityRepository = CityRepositoryImpl(cityDataSource)
        val getCitiesUseCase = GetCitiesUseCase(cityRepository)
        return ListPresenter(getCitiesUseCase)
    }
}