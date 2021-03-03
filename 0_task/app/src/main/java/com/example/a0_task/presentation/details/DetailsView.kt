package com.example.a0_task.presentation.details

import com.example.a0_task.domain.City
import com.example.a0_task.presentation.BaseView

interface DetailsView : BaseView {

    fun bindCity(city: City)

    fun closeScreen()
}