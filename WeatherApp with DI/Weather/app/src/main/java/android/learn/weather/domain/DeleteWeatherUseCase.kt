package android.learn.weather.domain

import javax.inject.Inject

class DeleteWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(code: Int) = repository.deleteWeather(code)
}