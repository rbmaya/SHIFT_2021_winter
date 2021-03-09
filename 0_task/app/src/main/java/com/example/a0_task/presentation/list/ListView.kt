package com.example.a0_task.presentation.list

import com.example.a0_task.presentation.BaseView
import com.example.a0_task.domain.city_model.City

interface ListView: BaseView {
    fun setIsLoading(loading: Boolean)

    fun bindCitiesList(list: List<City>)

    fun openCityDetailsScreen(name: String)
}