package android.learn.weather.domain

class DeleteWeatherUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(code: Int) = repository.deleteWeather(code)
}