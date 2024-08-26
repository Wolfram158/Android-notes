package android.learn.weather.domain

import android.learn.weather.data.network.model.WeatherDto
import androidx.lifecycle.LiveData

interface WeatherRepository {
    fun getWeatherList(): LiveData<List<Weather>>

    fun getWeather(code: Int): LiveData<Weather>

    suspend fun insertWeather(weatherDto: WeatherDto)

    suspend fun deleteWeather(code: Int)

    suspend fun findWeather(location: String): WeatherDto

    fun loadData()
}