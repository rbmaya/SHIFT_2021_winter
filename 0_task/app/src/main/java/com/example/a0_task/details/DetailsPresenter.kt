package com.example.a0_task.details

import com.example.a0_task.BasePresenter
import com.example.a0_task.CityRepository

class DetailPresenter(
    private val repository: CityRepository,
    private val id: Long
) : BasePresenter<DetailsView>() {

    override fun onViewAttached() {
        val city = repository.getCity(id)

        if (city != null) {
            view?.bindCity(city)
        } else {
            view?.closeScreen()
        }

    }
}