package android.learn.weather.domain

import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke(code: Int) = repository.getWeather(code)
}