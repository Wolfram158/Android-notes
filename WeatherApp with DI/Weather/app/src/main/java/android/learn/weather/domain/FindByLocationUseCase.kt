package android.learn.weather.domain

import javax.inject.Inject

class FindByLocationUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(location: String) = repository.findWeather(location)
}