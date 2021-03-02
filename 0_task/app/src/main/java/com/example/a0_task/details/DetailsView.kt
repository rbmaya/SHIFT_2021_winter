package com.example.a0_task.details

import com.example.a0_task.BaseView
import com.example.a0_task.City

interface DetailsView : BaseView {

    fun bindCity(city: City)

    fun closeScreen()
}