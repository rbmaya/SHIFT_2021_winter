package com.example.a0_task.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a0_task.CityApplication
import com.example.a0_task.CityRepository
import com.example.a0_task.R
import com.example.a0_task.details.DetailsActivity

class ListActivity : AppCompatActivity() {
    private lateinit var cityRepository: CityRepository

    private lateinit var citiesList: RecyclerView
    private val adapter = CityAdapter {
        DetailsActivity.start(this, it.id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        cityRepository = (application as CityApplication).cityRepository

        citiesList = findViewById(R.id.cities_list)
        citiesList.adapter = adapter
        citiesList.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        adapter.cities = cityRepository.getCities()
    }
}