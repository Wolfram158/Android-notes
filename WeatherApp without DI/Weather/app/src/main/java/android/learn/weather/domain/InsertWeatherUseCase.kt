package android.learn.weather.domain

import android.learn.weather.data.network.model.WeatherDto

class InsertWeatherUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(weatherDto: WeatherDto) = repository.insertWeather(weatherDto)
}