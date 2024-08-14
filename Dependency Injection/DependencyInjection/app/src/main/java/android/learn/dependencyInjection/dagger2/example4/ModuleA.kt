package android.learn.dependencyInjection.dagger2.example4

import dagger.Module
import dagger.Provides

@Module
class ModuleA {
    @Provides
    fun provideA(b: InterfaceB): A {
        return A(b)
    }

}