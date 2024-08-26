package android.learn.weather.domain

import javax.inject.Inject

class GetWeatherListUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke() = repository.getWeatherList()
}