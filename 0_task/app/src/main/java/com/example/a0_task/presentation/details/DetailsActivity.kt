package com.example.a0_task.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.a0_task.R
import com.example.a0_task.databinding.ActivityDetailsBinding
import com.example.a0_task.domain.city_model.City
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    companion object {
        private const val EXTRA_NAME = "EXTRA_NAME"

        fun start(context: Context, name: String) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(EXTRA_NAME, name)
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var viewModelAssistedFactory: DetailsViewModel.DetailsViewModelFactory

    val viewModel: DetailsViewModel by viewModels {
        val name = intent.getStringExtra(EXTRA_NAME)
        DetailsViewModel.provideFactory(viewModelAssistedFactory, name ?: "Unknown")
    }

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            viewModel.isLoading.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { isLoading ->
                    setIsLoading(isLoading)
                }
        }

        lifecycleScope.launch {
            viewModel.city.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { city ->
                    bindCity(city)
                }
        }

    }

    fun setIsLoading(loading: Boolean) {
        binding.progressBar.isVisible = loading
        binding.detailsLinearLayout.isVisible = !loading
    }

    fun bindCity(city: City) {
        binding.nameText.text = getString(R.string.city_format, city.name)
        val tempFar = (city.main.temp - 273).toInt()
        binding.temperatureText.text = getString(R.string.temperature_format, tempFar.toString())
        binding.precipitateText.text = getString(R.string.precipitate_format, city.weather[0].description)
        binding.countryText.text = getString(R.string.country_format, city.sys.country)

        val url = "http://openweathermap.org/img/wn/${city.weather[0].icon}@2x.png"
        Picasso.with(this).load(url).resize(40, 40).into(binding.precipitateIcon)

        binding.backButton.setOnClickListener {
            closeScreen()
        }
    }

    fun closeScreen() {
        finish()
    }

}