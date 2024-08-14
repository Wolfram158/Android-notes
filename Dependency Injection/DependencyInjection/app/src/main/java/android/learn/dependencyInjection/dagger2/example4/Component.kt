package android.learn.dependencyInjection.dagger2.example4

import dagger.Component

@Component(modules = [ModuleA::class, ModuleB::class])
interface Component {
    fun injectA(a: A)

    fun injectExample4(activity: Example4)

}