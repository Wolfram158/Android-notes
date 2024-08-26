package android.learn.weather.domain

import android.learn.weather.data.network.model.WeatherDto
import javax.inject.Inject

class InsertWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(weatherDto: WeatherDto) = repository.insertWeather(weatherDto)
}