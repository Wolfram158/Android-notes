package android.learn.weather.data.workers

import android.content.Context
import android.learn.weather.data.database.AppDatabase
import android.learn.weather.data.database.WeatherDao
import android.learn.weather.data.mapper.WeatherMapper
import android.learn.weather.data.network.ApiFactory
import android.learn.weather.data.network.ApiService
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val weatherDao: WeatherDao,
    private val apiService: ApiService,
    private val mapper: WeatherMapper
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        while (true) {
            try {
                val weathers = weatherDao.getWeatherListSuspend()
                for (weather in weathers) {
                    val weatherDto = apiService.getWeather(
                        arrayOf(weather.name, weather.region, weather.country)
                            .joinToString(separator = ",")
                    )
                    weatherDao.insertWeather(mapper.mapDtoToDbModel(weatherDto))
                }
            } catch (_: Exception) {
            }
            delay(60000)
        }
    }

    companion object {
        const val NAME = "RefreshDataWorker"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }

}