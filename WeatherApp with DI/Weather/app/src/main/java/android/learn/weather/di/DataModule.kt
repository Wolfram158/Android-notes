package android.learn.weather.di

import android.app.Application
import android.learn.weather.data.database.AppDatabase
import android.learn.weather.data.database.WeatherDao
import android.learn.weather.data.network.ApiFactory
import android.learn.weather.data.network.ApiService
import android.learn.weather.data.repository.WeatherRepositoryImpl
import android.learn.weather.domain.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @Binds
    @ApplicationScope
    fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

    companion object {
        @Provides
        @ApplicationScope
        fun provideWeatherDao(application: Application): WeatherDao {
            return AppDatabase.getInstance(application).getWeatherDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

    }
}