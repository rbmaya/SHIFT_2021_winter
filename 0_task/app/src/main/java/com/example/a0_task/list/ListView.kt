package com.example.a0_task.list

import com.example.a0_task.BaseView
import com.example.a0_task.City

interface ListView: BaseView {
    fun bindCitiesList(list: List<City>)

    fun openCityDetailsScreen(id: Long)
}