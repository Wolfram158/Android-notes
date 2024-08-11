package android.learn.weather.presentation

import android.app.Application
import android.learn.weather.data.network.model.WeatherDto
import android.learn.weather.data.repository.WeatherRepositoryImpl
import android.learn.weather.domain.DeleteWeatherUseCase
import android.learn.weather.domain.FindByLocationUseCase
import android.learn.weather.domain.GetWeatherListUseCase
import android.learn.weather.domain.GetWeatherUseCase
import android.learn.weather.domain.InsertWeatherUseCase
import android.learn.weather.domain.LoadDataUseCase
import androidx.lifecycle.AndroidViewModel

class WeatherViewModel(application: Application): AndroidViewModel(application) {
    private val repository = WeatherRepositoryImpl(application)

    private val getWeatherListUseCase = GetWeatherListUseCase(repository)
    private val getWeatherUseCase = GetWeatherUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)
    private val insertWeatherUseCase = InsertWeatherUseCase(repository)
    private val findByLocationUseCase = FindByLocationUseCase(repository)
    private val deleteWeatherUseCase = DeleteWeatherUseCase(repository)

    val weatherList = getWeatherListUseCase()

    fun getWeather(code: Int) = getWeatherUseCase(code)


    suspend fun insertWeather(weatherDto: WeatherDto) = insertWeatherUseCase(weatherDto)

    suspend fun findWeather(location: String) = findByLocationUseCase(location)

    suspend fun deleteWeather(code: Int) = deleteWeatherUseCase(code)

    init {
        loadDataUseCase()
    }

}