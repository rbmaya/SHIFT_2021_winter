package com.example.a0_task.domain.city_model

data class City (
    val id: Long,
    val name: String,
    val main: Main,
    val sys: Sys,
    val weather: List<Weather>
)