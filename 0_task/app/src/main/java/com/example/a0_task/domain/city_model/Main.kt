package com.example.a0_task.domain.city_model

import com.google.gson.annotations.SerializedName

data class Main(
    val temp: Double,
    @SerializedName("feels_like")
    val feelsLike: Double
)
