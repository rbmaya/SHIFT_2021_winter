package com.example.a0_task.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a0_task.R
import com.example.a0_task.databinding.ItemCityBinding
import com.example.a0_task.domain.city_model.City

class CityAdapter (private val onItemClick: (City) -> Unit) : RecyclerView.Adapter<CityAdapter.CityHolder>() {

    var cities: List<City> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemCityBinding = ItemCityBinding.inflate(layoutInflater, parent, false)
        return CityHolder(itemCityBinding, onItemClick)
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        val city = cities[position]
        holder.bind(city)
    }

    override fun getItemCount(): Int = cities.count()

    class CityHolder(private val itemCityBinding: ItemCityBinding,
                     private val onItemClick: (City) -> Unit) : RecyclerView.ViewHolder(itemCityBinding.root) {

        fun bind(city: City) {
            itemCityBinding.nameText.text = city.name
            val tempFar = (city.main.temp - 273).toInt()
            itemCityBinding.temperatureText.text = itemView.context.getString(R.string.temp_main_format, tempFar.toString())
            itemView.setOnClickListener { onItemClick(city) }
        }
    }
}