package android.learn.coroutinesPlayground.flow.example3DI.di

import android.app.Application
import android.learn.coroutinesPlayground.flow.example3DI.presentation.Example3Next
import android.learn.coroutinesPlayground.flow.example3DI.presentation.Example3Start
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(fragment: Example3Start)

    fun inject(fragment: Example3Next)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

}