package android.learn.dependencyInjection.dagger2.example6

import dagger.BindsInstance
import dagger.Component
import javax.inject.Named

@Component
interface Example6Component {
    fun inject(activity: Example6)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance @Named("x") x: Int,
            @BindsInstance @Named("y") y: Int
        ): Example6Component
    }
}