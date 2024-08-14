package android.learn.dependencyInjection.dagger2.example3

import dagger.Module
import dagger.Provides

@Module
class ModuleA {
    @Provides
    fun provideA(b: B): A {
        return A(b)
    }

}