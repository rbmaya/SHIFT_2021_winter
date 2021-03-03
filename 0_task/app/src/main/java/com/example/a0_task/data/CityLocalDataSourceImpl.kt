package com.example.a0_task.data

import com.example.a0_task.domain.City

class CityLocalDataSourceImpl: CityDataSource {

    private val cities = mutableListOf(
        City(0, "Moscow", "0", "snowy"),
        City(1, "Saint Petersburg", "+5", "cloudy"),
        City(2, "Novosibirsk", "-30", "sunny"),
        City(3, "Murmansk", "0", "cloudy"),
        City(4, "Voronezh", "-3", "snowy"),
        City(5, "Yakutsk", "-23", "sunny"),
        City(6, "Krasnodar", "+5", "rainy")
    )

    override fun getCity(id: Long): City? = cities.firstOrNull { it.id == id }

    override fun getCities(): List<City> = cities
}