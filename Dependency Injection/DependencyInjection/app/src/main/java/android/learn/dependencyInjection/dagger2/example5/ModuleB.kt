package android.learn.dependencyInjection.dagger2.example5

import dagger.Binds
import dagger.Module

@Module
interface ModuleB {
    @Binds
    fun bindB(b: B): InterfaceB

}