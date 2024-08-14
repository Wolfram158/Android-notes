package android.learn.coroutinesPlayground.flow.example3DI.presentation

import android.app.Application
import android.learn.coroutinesPlayground.flow.example3DI.di.DaggerApplicationComponent

class StringApp : Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}