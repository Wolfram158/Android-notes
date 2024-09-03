package android.learn.jetpackComposePlayground.examples5

import android.app.Application
import android.learn.jetpackComposePlayground.examples5.di.ApplicationComponent
import android.learn.jetpackComposePlayground.examples5.di.DaggerApplicationComponent

class App: Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}