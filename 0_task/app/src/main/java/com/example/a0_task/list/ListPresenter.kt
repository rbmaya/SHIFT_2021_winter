package com.example.a0_task.list

import com.example.a0_task.BasePresenter
import com.example.a0_task.City
import com.example.a0_task.CityRepository

class ListPresenter(private val repository: CityRepository) : BasePresenter<ListView>() {

    fun onViewResumed() {
        val personList = repository.getCities()

        view?.bindCitiesList(personList)
    }

    fun onPersonClicked(city: City) {
        view?.openCityDetailsScreen(city.id)
    }
}