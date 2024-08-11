package android.learn.weather.domain

class GetWeatherUseCase(
    private val repository: WeatherRepository
) {
    operator fun invoke(code: Int) = repository.getWeather(code)
}