package com.example.a0_task.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.a0_task.domain.city_model.City
import com.example.a0_task.R
import com.example.a0_task.databinding.ActivityDetailsBinding
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity(), DetailsView {
    companion object {
        private const val EXTRA_NAME = "EXTRA_NAME"

        fun start(context: Context, name: String) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(EXTRA_NAME, name)
            context.startActivity(intent)
        }
    }

    private val presenter by lazy {
        val name = intent.getStringExtra(EXTRA_NAME)
        DetailsPresenterFactory.getDetailsPresenter(name ?: "Unknown")
    }

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.loading.observe(this){
            binding.detailsLinearLayout.isVisible = !it
            binding.progressBar.isVisible = it
        }
        presenter.attachView(this)
    }

    override fun bindCity(city: City) {
        binding.nameText.text = getString(R.string.city_format, city.name)
        val tempFar = (city.main.temp - 273).toInt()
        binding.temperatureText.text = getString(R.string.temperature_format, tempFar.toString())
        binding.precipitateText.text = getString(R.string.precipitate_format, city.weather[0].description)
        binding.countryText.text = getString(R.string.country_format, city.sys.country)

        val url = "http://openweathermap.org/img/wn/${city.weather[0].icon}@2x.png"
        Picasso.with(this).load(url).resize(40, 40).into(binding.precipitateIcon)

        binding.backButton.setOnClickListener {
            presenter.getBack()
        }
    }

    override fun closeScreen() {
        finish()
    }

}