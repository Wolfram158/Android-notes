package android.learn.weather.data.repository

import android.app.Application
import android.learn.weather.data.database.AppDatabase
import android.learn.weather.data.mapper.WeatherMapper
import android.learn.weather.data.network.ApiFactory
import android.learn.weather.data.network.model.WeatherDto
import android.learn.weather.data.workers.RefreshDataWorker
import android.learn.weather.domain.Weather
import android.learn.weather.domain.WeatherRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager

class WeatherRepositoryImpl(
    private val application: Application
): WeatherRepository {
    private val weatherDao = AppDatabase.getInstance(application).getWeatherDao()
    private val weatherMapper = WeatherMapper()

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
        return weatherDao.insertWeather(weatherMapper.mapDtoToDbModel(weatherDto))
    }

    override suspend fun deleteWeather(code: Int) {
        weatherDao.deleteWeather(code)
    }

    override suspend fun findWeather(location: String): WeatherDto {
        return ApiFactory.apiService.getWeather(location)
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