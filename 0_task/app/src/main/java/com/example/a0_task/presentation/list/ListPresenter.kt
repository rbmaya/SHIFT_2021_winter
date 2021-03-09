package com.example.a0_task.presentation.list

import com.example.a0_task.presentation.BasePresenter
import com.example.a0_task.domain.city_model.City
import com.example.a0_task.domain.GetCitiesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListPresenter(private val getCitiesUseCase: GetCitiesUseCase) : BasePresenter<ListView>() {

    fun onViewResumed() {
        view?.setIsLoading(true)
        getCitiesUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate{
                view?.setIsLoading(false)
            }
            .subscribe({
                view?.bindCitiesList(it.list)
            }, {
                it.printStackTrace()
            })
            .untilDestroy()
    }

    fun onCityClicked(city: City) {
        view?.openCityDetailsScreen(city.name)
    }

    fun search(name: String){
        view?.openCityDetailsScreen(name)
    }
}