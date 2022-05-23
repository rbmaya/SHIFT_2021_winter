package com.example.a0_task.presentation.list

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.a0_task.R
import com.example.a0_task.databinding.ActivityListBinding
import com.example.a0_task.domain.city_model.City
import com.example.a0_task.presentation.details.DetailsActivity
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListActivity : AppCompatActivity() {

    private val viewModel: ListViewModel by viewModels()

    private lateinit var binding: ActivityListBinding

    private val adapter = CityAdapter {
        openCityDetailsScreen(it.name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        lifecycleScope.launch {
            viewModel.isLoading.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { isLoading ->
                    setIsLoading(isLoading)
                }
        }

        lifecycleScope.launch {
            viewModel.citiesList.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { cities ->
                    bindCitiesList(cities)
                }
        }

        binding.citiesList.adapter = adapter
    }

    private fun initViews() {
        Picasso
            .with(this)
            .load(R.drawable.search_icon)
            .resize(90, 90)
            .into(binding.searchButton)

        binding.searchButton.setOnClickListener {
            openCityDetailsScreen(binding.searchEditText.text.toString())
        }
    }

    fun setIsLoading(loading: Boolean) {
        binding.mainProgressBar.isVisible = loading
        binding.citiesList.isVisible = !loading
    }

    fun bindCitiesList(list: List<City>) {
        adapter.cities = list
    }

    private fun openCityDetailsScreen(name: String) {
        DetailsActivity.start(this, name)
    }
}