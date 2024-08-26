package android.learn.weather.presentation

import android.learn.weather.data.network.model.WeatherDto
import android.learn.weather.domain.DeleteWeatherUseCase
import android.learn.weather.domain.FindByLocationUseCase
import android.learn.weather.domain.GetWeatherListUseCase
import android.learn.weather.domain.GetWeatherUseCase
import android.learn.weather.domain.InsertWeatherUseCase
import android.learn.weather.domain.LoadDataUseCase
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val getWeatherListUseCase: GetWeatherListUseCase,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val loadDataUseCase: LoadDataUseCase,
    private val insertWeatherUseCase: InsertWeatherUseCase,
    private val findByLocationUseCase: FindByLocationUseCase,
    private val deleteWeatherUseCase: DeleteWeatherUseCase
) : ViewModel() {
    val weatherList = getWeatherListUseCase()

    fun getWeather(code: Int) = getWeatherUseCase(code)

    suspend fun insertWeather(weatherDto: WeatherDto) = insertWeatherUseCase(weatherDto)

    suspend fun findWeather(location: String) = findByLocationUseCase(location)

    suspend fun deleteWeather(code: Int) = deleteWeatherUseCase(code)

    init {
        loadDataUseCase()
    }

}