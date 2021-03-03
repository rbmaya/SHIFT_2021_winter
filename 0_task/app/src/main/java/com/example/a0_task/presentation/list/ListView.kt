package com.example.a0_task.presentation.list

import com.example.a0_task.presentation.BaseView
import com.example.a0_task.domain.City

interface ListView: BaseView {
    fun bindCitiesList(list: List<City>)

    fun openCityDetailsScreen(id: Long)
}