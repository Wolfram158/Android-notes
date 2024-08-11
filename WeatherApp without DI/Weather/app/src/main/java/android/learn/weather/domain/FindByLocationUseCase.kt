package android.learn.weather.domain

class FindByLocationUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(location: String) = repository.findWeather(location)
}