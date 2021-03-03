package com.example.a0_task.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.a0_task.domain.City
import com.example.a0_task.R
import com.example.a0_task.data.CityLocalDataSourceImpl
import com.example.a0_task.data.CityRepositoryImpl
import com.example.a0_task.domain.GetCityUseCase

class DetailsActivity : AppCompatActivity(), DetailsView {
    companion object {
        private const val EXTRA_ID = "EXTRA_ID"

        fun start(context: Context, id: Long) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(EXTRA_ID, id)
            context.startActivity(intent)
        }
    }

    private val presenter by lazy {
        val id = intent.getLongExtra(EXTRA_ID, 0)
        DetailsPresenterFactory.getDetailsPresenter(id)
    }

    private lateinit var nameText: TextView
    private lateinit var temperatureText: TextView
    private lateinit var precipitateText: TextView
    private lateinit var precipitateImage: ImageView
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        initViews()
        presenter.attachView(this)
    }

    private fun initViews() {
        nameText = findViewById(R.id.name_text)
        temperatureText = findViewById(R.id.temperature_text)
        precipitateText = findViewById(R.id.precipitate_text)
        precipitateImage = findViewById(R.id.precipitate_icon)
        backButton = findViewById(R.id.back_button)
    }

    override fun bindCity(city: City) {
        nameText.text = getString(R.string.city_format, city.name)
        temperatureText.text = getString(R.string.temperature_format, city.temperature)
        precipitateText.text = getString(R.string.precipitate_format, city.precipitate)
        when (city.precipitate) {
            "snowy" -> precipitateImage.setImageResource(R.drawable.snowy)
            "rainy" -> precipitateImage.setImageResource(R.drawable.rainy)
            "sunny" -> precipitateImage.setImageResource(R.drawable.sunny)
            "cloudy" -> precipitateImage.setImageResource(R.drawable.cloudy)
        }
        precipitateImage.invalidate()
        backButton.setOnClickListener {
            finish()
        }
    }

    override fun closeScreen() {
        finish()
    }

}