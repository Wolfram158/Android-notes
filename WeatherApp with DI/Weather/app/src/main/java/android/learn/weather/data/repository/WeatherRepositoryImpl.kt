package android.learn.weather.data.repository

import android.app.Application
import android.learn.weather.data.database.WeatherDao
import android.learn.weather.data.mapper.WeatherMapper
import android.learn.weather.data.network.ApiService
import android.learn.weather.data.network.model.WeatherDto
import android.learn.weather.data.workers.RefreshDataWorker
import android.learn.weather.domain.Weather
import android.learn.weather.domain.WeatherRepository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val application: Application,
    private val weatherDao: WeatherDao,
    private val apiService: ApiService,
    private val weatherMapper: WeatherMapper
) : WeatherRepository {
    override fun getWeatherList(): LiveData<List<Weather>> {
        return weatherDao.getWeatherList().map { it1 ->
            it1.map {
                weatherMapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getWeather(code: Int): LiveData<Weather> {
        return weatherDao.getWeather(code).map {
            weatherMapper.mapDbModelToEntity(it)
        }
    }

    override suspend fun insertWeather(weatherDto: WeatherDto) {
        Log.d("WeatherDao", weatherDao.toString())
        return weatherDao.insertWeather(weatherMapper.mapDtoToDbModel(weatherDto))
    }

    override suspend fun deleteWeather(code: Int) {
        weatherDao.deleteWeather(code)
    }

    override suspend fun findWeather(location: String): WeatherDto {
        Log.d("ApiService", apiService.toString())
        return apiService.getWeather(location)
    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }

}