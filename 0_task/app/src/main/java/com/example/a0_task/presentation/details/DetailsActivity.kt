package com.example.a0_task.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.a0_task.domain.city_model.City
import com.example.a0_task.R
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

    private lateinit var nameText: TextView
    private lateinit var temperatureText: TextView
    private lateinit var precipitateText: TextView
    private lateinit var precipitateImage: ImageView
    private lateinit var countryText: TextView
    private lateinit var backButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var linearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        initViews()
        presenter.loading.observe(this){
            linearLayout.isVisible = !it
            progressBar.isVisible = it
        }
        presenter.attachView(this)
    }

    private fun initViews() {
        nameText = findViewById(R.id.name_text)
        temperatureText = findViewById(R.id.temperature_text)
        precipitateText = findViewById(R.id.precipitate_text)
        precipitateImage = findViewById(R.id.precipitate_icon)
        countryText = findViewById(R.id.country_text)
        backButton = findViewById(R.id.back_button)
        progressBar = findViewById(R.id.progress_bar)
        linearLayout = findViewById(R.id.details_linear_layout)
    }

    override fun bindCity(city: City) {
        nameText.text = getString(R.string.city_format, city.name)
        val tempFar = (city.main.temp - 273).toInt()
        temperatureText.text = getString(R.string.temperature_format, tempFar.toString())
        precipitateText.text = getString(R.string.precipitate_format, city.weather[0].description)
        countryText.text = getString(R.string.country_format, city.sys.country)

        val url = "http://openweathermap.org/img/wn/${city.weather[0].icon}@2x.png"
        Picasso.with(this).load(url).resize(40, 40).into(precipitateImage)

        backButton.setOnClickListener {
            presenter.getBack()
        }
    }

    override fun closeScreen() {
        finish()
    }

}