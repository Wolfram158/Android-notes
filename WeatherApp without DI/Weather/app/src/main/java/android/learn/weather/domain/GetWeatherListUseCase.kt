package android.learn.weather.domain

class GetWeatherListUseCase(
    private val repository: WeatherRepository
) {
    operator fun invoke() = repository.getWeatherList()
}