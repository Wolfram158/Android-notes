package android.learn.dependencyInjection.dagger2.example3

import dagger.Module
import dagger.Provides

@Module
class ModuleB {
    @Provides
    fun provideB(): B {
        return B()
    }

}