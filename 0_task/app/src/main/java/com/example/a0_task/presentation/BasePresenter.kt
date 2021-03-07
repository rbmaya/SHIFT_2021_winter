package com.example.a0_task.presentation

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter<T : BaseView> {

    private val compositeDisposable = CompositeDisposable()
    var view: T? = null

    fun Disposable.untilDestroy() = compositeDisposable.add(this)

    fun attachView(view: T) {
        this.view = view

        onViewAttached()
    }

    open fun onViewAttached() {}

    fun destroy() {
        compositeDisposable.clear()
        view = null
    }
}