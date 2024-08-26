package android.learn.weather.presentation

import android.app.Application
import android.learn.weather.data.workers.RefreshDataWorkerFactory
import android.learn.weather.di.DaggerApplicationComponent
import androidx.work.Configuration
import javax.inject.Inject

class App : Application(), Configuration.Provider {
    @Inject
    lateinit var refreshDataWorkerFactory: RefreshDataWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(refreshDataWorkerFactory)
            .build()

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)

        super.onCreate()
    }
}