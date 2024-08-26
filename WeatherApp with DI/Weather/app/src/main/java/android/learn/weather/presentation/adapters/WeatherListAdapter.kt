package android.learn.weather.presentation.adapters

import android.learn.weather.databinding.LocalWeatherItemBinding
import android.learn.weather.domain.Weather
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso

class WeatherListAdapter: ListAdapter<Weather, WeatherViewHolder>(WeatherDiffUtilCallback) {
    var onWeatherClickListener: OnWeatherClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = LocalWeatherItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather = getItem(position)
        holder.binding.textLocation.text = if (weather.region.contains(weather.name)) {
            String.format("%s, %s", weather.region, weather.country)
        } else {
            String.format("%s, %s, %s", weather.country, weather.region, weather.name)
        }
        holder.binding.textLastUpdate.text = weather.lastUpdated
        holder.binding.textWeatherInfo.text = weather.tempC
        Picasso.get().load("https:${weather.imageUrl}").into(holder.binding.imageCloud)
        holder.binding.root.setOnClickListener {
            onWeatherClickListener?.onWeatherClick(weather)
        }
    }

    interface OnWeatherClickListener {
        fun onWeatherClick(weather: Weather)
    }
}