package com.example.a0_task.presentation.details

import com.example.a0_task.domain.GetCityUseCase
import com.example.a0_task.presentation.BasePresenter

class DetailPresenter(
    private val getCityUseCase: GetCityUseCase,
    private val id: Long
) : BasePresenter<DetailsView>() {

    override fun onViewAttached() {
        val city = getCityUseCase(id)

        if (city != null) {
            view?.bindCity(city)
        } else {
            view?.closeScreen()
        }

    }
}