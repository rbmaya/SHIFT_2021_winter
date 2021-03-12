package com.example.a0_task.presentation.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a0_task.R
import com.example.a0_task.databinding.ActivityListBinding
import com.example.a0_task.domain.city_model.City
import com.example.a0_task.presentation.details.DetailsActivity
import com.squareup.picasso.Picasso

class ListActivity : AppCompatActivity(), ListView {
    private val presenter by lazy {
        ListPresenterFactory.getListPresenter()
    }

    private lateinit var binding: ActivityListBinding

    private val adapter = CityAdapter {
        presenter.onCityClicked(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        presenter.attachView(this)

        binding.citiesList.adapter = adapter
        binding.citiesList.layoutManager = LinearLayoutManager(this)
    }

    fun initViews(){
        Picasso
            .with(this)
            .load(R.drawable.search_icon)
            .resize(90, 90)
            .into(binding.searchButton)
        binding.searchButton.setOnClickListener {
            presenter.search(binding.searchEditText.text.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewResumed()
    }

    override fun setIsLoading(loading: Boolean) {
        binding.mainProgressBar.isVisible = loading
        binding.citiesList.isVisible = !loading
    }

    override fun bindCitiesList(list: List<City>) {
        adapter.cities = list
    }

    override fun openCityDetailsScreen(name: String) {
        DetailsActivity.start(this, name)
    }
}