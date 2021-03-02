package com.example.a0_task.list

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a0_task.City
import com.example.a0_task.CityApplication
import com.example.a0_task.R
import com.example.a0_task.details.DetailsActivity

class ListActivity : AppCompatActivity(), ListView {
    private val presenter by lazy {
        ListPresenter((application as CityApplication).cityRepository)
    }

    private lateinit var citiesList: RecyclerView
    private val adapter = CityAdapter {
        presenter.onPersonClicked(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        presenter.attachView(this)

        citiesList = findViewById(R.id.cities_list)
        citiesList.adapter = adapter
        citiesList.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewResumed()
    }

    override fun bindCitiesList(list: List<City>) {
        adapter.cities = list
    }

    override fun openCityDetailsScreen(id: Long) {
        DetailsActivity.start(this, id)
    }
}