package com.example.a0_task.presentation.details

import com.example.a0_task.domain.GetCityUseCase
import com.example.a0_task.presentation.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailPresenter(
    private val getCityUseCase: GetCityUseCase,
    private val name: String
) : BasePresenter<DetailsView>() {

    override fun onViewAttached() {
        view?.setIsLoading(true)
        getCityUseCase(name)
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate{
                view?.setIsLoading(false)
            }
            .subscribe({
                view?.bindCity(it)
            }, {
                it.printStackTrace()
                view?.closeScreen()
            })
            .untilDestroy()
    }

    fun getBack(){
        view?.closeScreen()
    }
}