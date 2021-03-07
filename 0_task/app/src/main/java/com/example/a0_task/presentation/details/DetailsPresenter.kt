package com.example.a0_task.presentation.details

import androidx.lifecycle.MutableLiveData
import com.example.a0_task.domain.GetCityUseCase
import com.example.a0_task.presentation.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailPresenter(
    private val getCityUseCase: GetCityUseCase,
    private val name: String
) : BasePresenter<DetailsView>() {

    val loading = MutableLiveData<Boolean>()

    override fun onViewAttached() {
        loading.value = true
        getCityUseCase(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate{
                loading.value = false
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