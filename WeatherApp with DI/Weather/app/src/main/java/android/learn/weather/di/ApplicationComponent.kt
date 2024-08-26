package android.learn.weather.di

import android.app.Application
import android.learn.weather.presentation.AddWeatherFragment
import android.learn.weather.presentation.App
import android.learn.weather.presentation.WeatherFragment
import android.learn.weather.presentation.WeatherListActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(fragment: WeatherFragment)

    fun inject(fragment: AddWeatherFragment)

    fun inject(activity: WeatherListActivity)

    fun inject(application: App)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

}