package com.example.a0_task.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.a0_task.CityApplication
import com.example.a0_task.CityRepository
import com.example.a0_task.R

class DetailsActivity: AppCompatActivity() {
    companion object {
        private const val EXTRA_ID = "EXTRA_ID"

        fun start(context: Context, id: Long) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(EXTRA_ID, id)
            context.startActivity(intent)
        }
    }

    private lateinit var cityRepository: CityRepository

    private lateinit var nameText: TextView
    private lateinit var temperatureText: TextView
    private lateinit var precipitateText: TextView
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        cityRepository = (application as CityApplication).cityRepository

        nameText = findViewById(R.id.name_text)
        temperatureText = findViewById(R.id.temperature_text)
        precipitateText = findViewById(R.id.precipitate_text)
        backButton = findViewById(R.id.back_button)

        backButton.setOnClickListener {
            finish()
        }

        initPerson()
    }

    private fun initPerson() {
        val id = intent.getLongExtra(EXTRA_ID, 0)
        val city = cityRepository.getCity(id)

        if (city != null) {
            nameText.text = getString(R.string.city_format, city.name)
            temperatureText.text = getString(R.string.temperature_format, city.temperature)
            precipitateText.text = getString(R.string.precipitate_format, city.precipitate)
        } else {
            finish()
        }
    }

}