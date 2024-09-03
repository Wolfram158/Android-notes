package android.learn.jetpackComposePlayground.examples5.di

import android.learn.jetpackComposePlayground.examples5.Example5Activity
import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        PresentationModule::class
    ]
)
interface ApplicationComponent {
    fun inject(activity: Example5Activity)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent

    }

}