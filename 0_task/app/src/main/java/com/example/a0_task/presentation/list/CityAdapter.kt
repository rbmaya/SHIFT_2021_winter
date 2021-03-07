package com.example.a0_task.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a0_task.R
import com.example.a0_task.domain.city_model.City

class CityAdapter (private val onItemClick: (City) -> Unit) : RecyclerView.Adapter<CityAdapter.CityHolder>() {

    var cities: List<City> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return CityHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        val city = cities[position]
        holder.bind(city)
    }

    override fun getItemCount(): Int = cities.count()

    class CityHolder(itemView: View, private val onItemClick: (City) -> Unit) : RecyclerView.ViewHolder(itemView) {

        private val nameText = itemView.findViewById<TextView>(R.id.name_text)
        private val temperatureText = itemView.findViewById<TextView>(R.id.temperature_text)

        fun bind(city: City) {
            nameText.text = city.name
            val tempFar = (city.main.temp - 273).toInt()
            temperatureText.text = itemView.context.getString(R.string.temp_main_format, tempFar.toString())
            itemView.setOnClickListener { onItemClick(city) }
        }
    }
}