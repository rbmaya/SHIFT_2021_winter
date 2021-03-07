package com.example.a0_task.presentation.list

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a0_task.R
import com.example.a0_task.domain.city_model.City
import com.example.a0_task.presentation.details.DetailsActivity
import com.squareup.picasso.Picasso

class ListActivity : AppCompatActivity(), ListView {
    private val presenter by lazy {
        ListPresenterFactory.getListPresenter()
    }
    private lateinit var citiesList: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var searchButton: ImageButton
    private lateinit var progressBar: ProgressBar


    private val adapter = CityAdapter {
        presenter.onCityClicked(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        initViews()

        presenter.attachView(this)
        presenter.loading.observe(this){
            citiesList.isVisible = !it
            progressBar.isVisible = it
        }

        citiesList.adapter = adapter
        citiesList.layoutManager = LinearLayoutManager(this)
    }

    fun initViews(){
        citiesList = findViewById(R.id.cities_list)
        searchEditText = findViewById(R.id.search_edit_text)
        searchButton = findViewById(R.id.search_button)
        Picasso
            .with(this)
            .load(R.drawable.search_icon)
            .resize(90, 90)
            .into(searchButton)
        searchButton.setOnClickListener {
            presenter.search(searchEditText.text.toString())
        }
        progressBar = findViewById(R.id.main_progress_bar)
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewResumed()
    }

    override fun bindCitiesList(list: List<City>) {
        adapter.cities = list
    }

    override fun openCityDetailsScreen(name: String) {
        DetailsActivity.start(this, name)
    }
}