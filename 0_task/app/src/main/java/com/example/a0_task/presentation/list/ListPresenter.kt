package com.example.a0_task.presentation.list

import com.example.a0_task.presentation.BasePresenter
import com.example.a0_task.domain.City
import com.example.a0_task.domain.CityRepository
import com.example.a0_task.domain.GetCitiesUseCase

class ListPresenter(private val getCitiesUseCase: GetCitiesUseCase) : BasePresenter<ListView>() {

    fun onViewResumed() {
        val personList = getCitiesUseCase()

        view?.bindCitiesList(personList)
    }

    fun onPersonClicked(city: City) {
        view?.openCityDetailsScreen(city.id)
    }
}