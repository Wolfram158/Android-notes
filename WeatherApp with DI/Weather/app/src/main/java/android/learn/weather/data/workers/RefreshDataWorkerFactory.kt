package android.learn.weather.data.workers

import android.content.Context
import android.learn.weather.data.database.WeatherDao
import android.learn.weather.data.mapper.WeatherMapper
import android.learn.weather.data.network.ApiService
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject

class RefreshDataWorkerFactory @Inject constructor(
    private val weatherDao: WeatherDao,
    private val apiService: ApiService,
    private val mapper: WeatherMapper
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        return RefreshDataWorker(
            context = appContext,
            workerParameters = workerParameters,
            weatherDao = weatherDao,
            apiService = apiService,
            mapper = mapper
        )
    }
}