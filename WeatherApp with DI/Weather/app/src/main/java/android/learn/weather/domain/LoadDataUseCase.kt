package android.learn.weather.domain

import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke() = repository.loadData()
}