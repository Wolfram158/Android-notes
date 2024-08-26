package android.learn.weather.presentation.adapters

import android.learn.weather.domain.Weather
import androidx.recyclerview.widget.DiffUtil

object WeatherDiffUtilCallback: DiffUtil.ItemCallback<Weather>() {
    override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
        return oldItem.name == newItem.name && oldItem.region == newItem.region &&
                oldItem.country == newItem.country
    }

    override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
        return oldItem == newItem
    }
}