package com.example.a0_task.presentation.list

import com.example.a0_task.domain.GetCitiesUseCase
import com.example.a0_task.domain.city_model.City
import com.example.a0_task.domain.city_model.Response
import com.example.a0_task.presentation.list.ListPresenter
import com.example.a0_task.presentation.list.ListView
import io.mockk.*
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test


class ListPresenterTest {
    private val view: ListView = mockk(relaxed = true)
    private val city: City = mockk(relaxed = true)
    private val response: Response = Response(listOf(city))
    private val getCitiesUseCase: GetCitiesUseCase = mockk()
    private val listPresenter = ListPresenter(getCitiesUseCase)

    @Before
    fun `attach view and set init main thread`() {
        listPresenter.attachView(view)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            Schedulers.trampoline()
        }
    }

    @Test
    fun `on view resumed EXPECT bind cities list and set is loading`() {
        every { getCitiesUseCase() } returns Single.just(response)

        listPresenter.onViewResumed()

        verifyAll {
            view.setIsLoading(true)
            view.setIsLoading(false)
            view.bindCitiesList(response.list)
        }
    }

    @Test
    fun `on city clicked EXPECT open selected city details screen`() {
        every { view.openCityDetailsScreen(city.name) } just runs

        listPresenter.onCityClicked(city)

        verify { view.openCityDetailsScreen(city.name) }
    }

    @Test
    fun `search EXPECT open city by entered name`() {
        every { view.openCityDetailsScreen(String()) } just runs

        listPresenter.search(String())

        verify { view.openCityDetailsScreen(String()) }
    }
}