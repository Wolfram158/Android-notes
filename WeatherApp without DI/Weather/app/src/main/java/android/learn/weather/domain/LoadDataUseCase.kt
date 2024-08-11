package android.learn.weather.domain

class LoadDataUseCase(
    private val repository: WeatherRepository
) {
    operator fun invoke() = repository.loadData()
}